/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.CooperativaDao;
import com.nttdata.idmccnobe.dao.UserBeDao;
import com.nttdata.idmccnobe.dto.Common;
import com.nttdata.idmccnobe.dto.CreateUserBeRequest;
import com.nttdata.idmccnobe.dto.GenericDto;
import com.nttdata.idmccnobe.dto.SimpleResult;
import com.nttdata.idmccnobe.dto.UpdatePasswordReqDto;
import com.nttdata.idmccnobe.dto.UserBeDto;
import com.nttdata.idmccnobe.dto.UserBeRequest;
import com.nttdata.idmccnobe.dto.UserBeResDto;
import com.nttdata.idmccnobe.dto.UserBeSearchResPayloadDto;
import com.nttdata.idmccnobe.enumeration.ErrorCode;
import com.nttdata.idmccnobe.model.UserBe;
import com.nttdata.idmccnobe.util.CommonUtils;
import com.nttdata.idmccnobe.util.PasswordUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DelorenziVa
 */
@Service("UserBeService")
@Transactional
public class UserBeService extends AbstractService{
    
    @Autowired
    private UserBeDao userBeDao;
    
    @Autowired
    private CooperativaDao cooperativaDao;
    
    public UserBeResDto searchUserBe(UserBeRequest userBeRequest, String userRole) {
        UserBeResDto userBeResDto = new UserBeResDto();
        UserBeSearchResPayloadDto payload = new UserBeSearchResPayloadDto();
        List<UserBeDto> userDtos = new ArrayList<>();
        String username = userBeRequest.getUsername();
        String coop = userBeRequest.getCoop();
        String role = userBeRequest.getRole();
        String customerCancellation = userBeRequest.getCustomerCancellation();
        
        List<UserBe> userEntities = userBeDao.find(username, coop, role, customerCancellation);

        UserBeDto userDto = new UserBeDto();
        for (UserBe user : userEntities) {
            if (user.getRole().equals("ADMIN")) {
                if (userRole.equals("ADMIN")) {
                    // solo i superamministratori possono vedere i super amministratori
                    userDto = user.toDto(user);
                    userDtos.add(userDto);
                }
            } else {
                userDto = user.toDto(user);
                userDtos.add(userDto);
            }
        }
        
        payload.setBeUsers(userDtos);
        userBeResDto.setPayload(payload);
        return userBeResDto;
    }
    
    public boolean deleteUserBe(String id) {
        boolean result = true;
        UserBe user = userBeDao.getByKey(Integer.valueOf(id));
        if (user!=null) {
            userBeDao.delete(user);
        } else {
            result = false;
        }
        return result;
    }
    
    public GenericDto createUserBe(CreateUserBeRequest createUserBeRequest) {
        GenericDto genericDto = new GenericDto();
        UserBe newUser = new UserBe();
        UserBe user = userBeDao.findByUsername(createUserBeRequest.getUsername().trim());
        if (user!=null) {
            genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.EXISTING_USERNAME));
        } else {
            // 0: controllo che la password non abbia spazi vuoti
            if (!containsBlankSpaces(createUserBeRequest.getPassword())) {
                // 1: controllo che newPassword e repeatNewPassword siano uguali (case sensitive)
                if (newPasswordEqualsRepeatNewPassword(createUserBeRequest.getPassword(), createUserBeRequest.getRepeatPassword())) {
                    // 2: controllo che la password rispetti i criteri di sicurezza
                    if(isSecurePassword(createUserBeRequest.getPassword())){
                        if (!CommonUtils.nullToEmpty(createUserBeRequest.getCoop()).equals("") || (CommonUtils.nullToEmpty(createUserBeRequest.getCoop()).equals("") && (createUserBeRequest.getRole().equals("ADMIN") || createUserBeRequest.getRole().equals("CCNO")))) {
                            Integer coopId = (createUserBeRequest.getRole().equals("ADMIN") || createUserBeRequest.getRole().equals("CCNO") ? Integer.valueOf("0") : Integer.valueOf(createUserBeRequest.getCoop()));
                            UserBe userr = new UserBe(
                                createUserBeRequest.getUsername(),
                                PasswordUtils.encode(createUserBeRequest.getPassword()),
                                createUserBeRequest.getRole(),
                                cooperativaDao.getByKey(coopId),
                                Integer.parseInt(createUserBeRequest.getCustomerCancellation())
                            );
                            userBeDao.persist(
                                userr
                            );
    //                        newUser.setUsername(createUserBeRequest.getUsername());
    //                        newUser.setPassword(createUserBeRequest.getPassword());
    //                        newUser.setCooperativa(cooperativaDao.getByKey(Integer.valueOf(createUserBeRequest.getCoop())));
    //                        newUser.setRole(createUserBeRequest.getRole());
    //                        userBeDao.saveOrUpdate(newUser);
    //                        userBeDao.persist(user);
                            genericDto.setHeader(CommonUtils.setHeaderResultOK());
                        } else {
                            genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.ALL_INPUT_REQUIRED));
                        }
                    } else {
                        genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.PASSWORD_NOT_SECURE));
                    } 
                } else {
                    genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.PASSWORD_DOESNT_MATCH));
                }
            } else {
                genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.PASSWORD_WITH_BLANK_SPACES));
            }
        }
        return genericDto;
    }
    
    public GenericDto updatePassword(UpdatePasswordReqDto updatePasswordReqDto) {
        
        GenericDto genericDto = new GenericDto();
        
        UserBe user = userBeDao.findByUsername(updatePasswordReqDto.getUsername());
        if (user!=null && PasswordUtils.matches(updatePasswordReqDto.getCurrentPassword(), user.getPassword())) {
            // 0: controllo che la password non abbia spazi vuoti
            if (!containsBlankSpaces(updatePasswordReqDto.getNewPassword())) {
                // 1: controllo che newPassword e repeatNewPassword siano uguali (case sensitive)
                if (newPasswordEqualsRepeatNewPassword(updatePasswordReqDto.getNewPassword(), updatePasswordReqDto.getRepeatNewPassword())) {
                    // 2: controllo che la password rispetti i criteri di sicurezza
                    if(isSecurePassword(updatePasswordReqDto.getNewPassword())){
                        user.setPassword(PasswordUtils.encode(updatePasswordReqDto.getNewPassword()));
                        userBeDao.saveOrUpdate(user);
                        genericDto.setHeader(CommonUtils.setHeaderResultOK());
                    } else {
                        genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.PASSWORD_NOT_SECURE));
                    } 
                } else {
                    genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.PASSWORD_DOESNT_MATCH));
                }
            } else {
                genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.PASSWORD_WITH_BLANK_SPACES));
            }
            
        } else {
            genericDto.setHeader(CommonUtils.setHeaderResultKO(ErrorCode.WRONG_CURRENT_PASSWORD));
        }
        
        return genericDto;
    }
    
    public boolean newPasswordEqualsRepeatNewPassword(String newPassword, String repeatNewPassword) {
        return newPassword.trim().equals(repeatNewPassword.trim());
    }
    
    public boolean containsBlankSpaces(String newPassword) {
        boolean hasBlankSpaces = false;
        int i;
        for (i = 0; i < newPassword.length(); i++) {
            char x = newPassword.charAt(i);
            if (Character.isWhitespace(x)){
                hasBlankSpaces = true;
                break;
            }
        }
        return hasBlankSpaces;
    }
    public boolean isSecurePassword(String newPassword) {
        boolean isSecure;
        int i;
        boolean hasLowerCaseChar = false;
        boolean hasUpperCaseChar = false;
        boolean hasDigit = false;
        if (newPassword.trim().length() >= 8) {
            for (i = 0; i < newPassword.length(); i++) {
                char x = newPassword.charAt(i);
                if (Character.isDigit(x)){
                    hasDigit = true;
                } else if (Character.isLowerCase(x)) {
                    hasLowerCaseChar = true;
                } else if (Character.isUpperCase(x)) {
                    hasUpperCaseChar = true;
                }
                if (hasDigit && hasLowerCaseChar && hasUpperCaseChar) {
                    break;
                }
            }
            Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(newPassword);
            boolean findSpecialChar = matcher.find();
            
            if (hasDigit && hasLowerCaseChar && hasUpperCaseChar && findSpecialChar) {
                isSecure = true;
            } else {
                isSecure = false;
            }
        } else {
            isSecure = false;
        }
        return isSecure;
    }
}

$ErrorActionPreference = "Stop"

$tokenUrl = "http://127.0.0.1:8081/realms/IdmCCNO/protocol/openid-connect/token"
$body = @{
    grant_type = "password"
    client_id = "idmCCNOClient"
    username = $env:IDMCCNO_TEST_USERNAME
    password = $env:IDMCCNO_TEST_PASSWORD
}

if (-not $body.username -or -not $body.password) {
    throw "Set IDMCCNO_TEST_USERNAME and IDMCCNO_TEST_PASSWORD before running this script."
}

$response = Invoke-RestMethod -Method Post -Uri $tokenUrl -Body $body -ContentType "application/x-www-form-urlencoded"
if (-not $response.access_token) {
    throw "Keycloak did not return an access_token."
}

Write-Host "Keycloak token OK"

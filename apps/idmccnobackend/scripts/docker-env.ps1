$ErrorActionPreference = "Stop"

$dockerBin = "C:\Program Files\Rancher Desktop\resources\resources\win32\bin"
$dockerPlugins = "C:\Program Files\Rancher Desktop\resources\resources\win32\docker-cli-plugins"

if (Test-Path (Join-Path $dockerBin "docker.exe")) {
    $env:PATH = "$dockerBin;$dockerPlugins;$env:PATH"
}

function Invoke-Docker {
    & "docker" @args
}

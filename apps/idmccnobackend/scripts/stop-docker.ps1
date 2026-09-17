$ErrorActionPreference = "Stop"
. "$PSScriptRoot\docker-env.ps1"

$root = Split-Path -Parent $PSScriptRoot
Push-Location $root
try {
    docker compose down
} finally {
    Pop-Location
}

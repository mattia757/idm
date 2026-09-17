param(
    [switch]$Reset
)

$ErrorActionPreference = "Stop"
. "$PSScriptRoot\docker-env.ps1"

$root = Split-Path -Parent $PSScriptRoot
Push-Location $root
try {
    if ($Reset) {
        docker compose down -v
    }
    docker compose up -d --build
    docker compose ps
} finally {
    Pop-Location
}

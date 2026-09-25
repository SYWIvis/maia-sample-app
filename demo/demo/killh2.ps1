#this is to kill persistent h2 console more quickly.
function kill_persistenth2 {
    Get-Process | Where-Object {$_.ProcessName -like "*java*"}
    Get-process -Name "javaw" -ErrorAction SilentlyContinue | Stop-Process -Force
    Get-process -Name "java" -ErrorAction SilentlyContinue | Stop-Process -Force
}
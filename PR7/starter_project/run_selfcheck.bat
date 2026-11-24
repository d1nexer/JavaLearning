@echo off
cd /d "%~dp0"
if exist out rmdir /s /q out
mkdir out
if exist sources.txt del sources.txt

powershell -NoProfile -Command "Get-ChildItem -Path 'src' -Filter '*.java' -Recurse | ForEach-Object { $_.FullName.Replace('\', '/') } | Out-File -FilePath 'sources.txt' -Encoding ascii"

javac -encoding UTF-8 -d out @sources.txt
if exist sources.txt del sources.txt
java -cp out ru.edu.pr04.SelfCheck
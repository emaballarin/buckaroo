wget https://github.com/dgiagio/warp/releases/download/v0.3.0/windows-x64.warp-packer.exe -OutFile warp-packer.exe

dotnet publish ./buckaroo-cli/ -c Release -r win10-x64

md .\warp -ea 0

.\warp-packer.exe --arch windows-x64 --input_dir .\buckaroo-cli\bin\intel64\Release\netcoreapp3.0\win10-x64 --exec buckaroo-cli.exe --output .\warp\buckaroo-windows.exe

.\warp\buckaroo-windows.exe

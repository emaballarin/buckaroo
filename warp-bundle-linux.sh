#!/bin/bash

export CppCompilerAndLinker=clang

wget -c -O warp-packer https://github.com/dgiagio/warp/releases/download/v0.3.0/linux-x64.warp-packer

chmod +x ./warp-packer
./warp-packer --version

dotnet publish ./buckaroo-cli/ -c Release -r linux-x64

mkdir -p warp
rm -rf ./warp/buckaroo-linux

./warp-packer --arch linux-x64 --exec buckaroo-cli --input_dir ./buckaroo-cli/bin/intel64/Release/netcoreapp3.1/linux-x64 --output warp/buckaroo-linux
./warp/buckaroo-linux

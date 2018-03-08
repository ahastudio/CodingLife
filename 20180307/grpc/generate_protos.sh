mkdir -p lib
grpc_tools_ruby_protoc -I ./protos \
    --ruby_out=lib --grpc_out=lib \
    ./protos/*.proto

const {
    OPCUAClient,
    MessageSecurityMode,
    SecurityPolicy,
    AttributeIds,
} = require("node-opcua");

const connectionStrategy = {
    initialDelay: 5_000,
    maxRetry: 1,
};

const client = OPCUAClient.create({
    applicationName: "OPC-UA Test",
    connectionStrategy: connectionStrategy,
    securityMode: MessageSecurityMode.None,
    securityPolicy: SecurityPolicy.None,
    endpointMustExist: false,
    transportSettings: {
        maxMessageSize: 1024 * 1024 * 1024,
    },
});

const endpointUrl = "opc.tcp://localhost:7560";

async function main() {
    await client.connect(endpointUrl);
    console.log("Connected!");

    const session = await client.createSession();
    console.log("Session created!");

    const browseResult = await session.browse("RootFolder");

    const maxAge = 0;
    const node = {
        nodeId: "ns=4;s=92677f56-c02e-4038-abd4-212bf8fe0187",
        attributeId: AttributeIds.Value,
    };
    const value = await session.read(node, maxAge);

    console.log("------------------------");
    console.log(value.sourceTimestamp);
    console.log("------------------------");
    console.log(value.statusCode);
    console.log("------------------------");
    console.log(value.value.value);
    console.log("------------------------");

    await session.close();

    await client.disconnect();

    console.log("Done!");
}

main()

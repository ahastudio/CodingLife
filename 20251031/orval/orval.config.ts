import { defineConfig } from "orval";

export default defineConfig({
	api: {
		input: {
			target: "./openapi.json",
		},
		output: {
			mode: "tags",
			target: "src/api/",
			schemas: "src/models/",
			client: "fetch",
			// mock을 true로 설정하면 대규모 스펙에서 생성 속도 저하 및 에러 발생
			// Reference: https://orval.dev/reference/configuration/output#mock
			mock: false,
		},
	},
});

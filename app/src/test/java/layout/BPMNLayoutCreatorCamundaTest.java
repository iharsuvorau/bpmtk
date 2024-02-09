package layout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BPMNLayoutCreatorCamundaTest {

    @Test
    void createLayout() throws Exception {
        String bpmnModel = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" exporter=\"ProM. http://www.promtools.org/prom6\" exporterVersion=\"6.3\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.omg.org/bpmn20\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\">\n" +
                "  <process id=\"proc_1119072377\" isClosed=\"false\" processType=\"None\">\n" +
                "<startEvent id=\"node_164f56bc-12ca-43ec-823f-a5f481dc2a0d\" isInterrupting=\"true\" name=\"\" parallelMultiple=\"false\"/>\n" +
                "<endEvent id=\"node_10fc9b99-5bd8-4878-b043-0de983821211\" name=\"\"/>\n" +
                "<task completionQuantity=\"1\" id=\"node_1dcc87ee-08b7-4aaf-a756-e8f489e373f1\" isForCompensation=\"false\" name=\"Final Inspection Q.C.\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_ade5e46b-fb90-4839-8f80-dc20feefca2b\" isForCompensation=\"false\" name=\"Nitration Q.C.\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_a2398cb6-3eda-4b5e-9913-71d01438c4e8\" isForCompensation=\"false\" name=\"Packing\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_b2e0b726-30f5-43e9-89c4-041d593ae23a\" isForCompensation=\"false\" name=\"Flat Grinding\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_e93bf2b2-8903-43cf-928e-382d1ecb1bf9\" isForCompensation=\"false\" name=\"Start\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_0af32f51-93a5-4bcd-a7dd-e0fef53b6cd0\" isForCompensation=\"false\" name=\"Setup\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_ce118ed3-7b12-42c9-82ea-55f896617ef2\" isForCompensation=\"false\" name=\"Lapping\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_358a5db3-c523-4268-8c88-42674ed2bc0a\" isForCompensation=\"false\" name=\"Round Grinding\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_d38b3dc4-5411-4579-9b6b-31463d34358a\" isForCompensation=\"false\" name=\"Turn &amp; Mill. &amp; Screw Assem\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_6fc429a9-7695-446f-92f2-937e854125a5\" isForCompensation=\"false\" name=\"Turning Q.C.\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_b0aeb4d1-63bc-440f-8acd-c8182929aaed\" isForCompensation=\"false\" name=\"Rework Milling\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_f52acf4e-eb1f-4edf-98d7-e09e89874ab3\" isForCompensation=\"false\" name=\"Turning &amp; Milling\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_7df46f9e-6a9a-4d55-bdc9-8c361c9fefba\" isForCompensation=\"false\" name=\"Final Inspection\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_ed81b338-0b45-44e3-834f-db859b37b128\" isForCompensation=\"false\" name=\"Stress Relief\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_76d9f547-7067-453e-ad07-0006156501cb\" isForCompensation=\"false\" name=\"Milling\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_844fbeda-0c8e-481c-90e2-dd7f95097134\" isForCompensation=\"false\" name=\"Grinding Rework\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_9d0fee08-a6a8-46a0-8305-7d50a7577efb\" isForCompensation=\"false\" name=\"Laser Marking\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_fb0462da-8de5-41fc-bd0e-408279cbc38a\" isForCompensation=\"false\" name=\"Round  Q.C.\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_545cde46-c830-4c30-839a-b400a652c54a\" isForCompensation=\"false\" name=\"Turning Rework\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_f9c6a777-9756-46ec-8654-a7768dc75888\" isForCompensation=\"false\" name=\"SETUP     Turning &amp; Milling\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_fafb8a71-1f14-4f99-a37b-ce93b98d94b6\" isForCompensation=\"false\" name=\"Milling Q.C.\" startQuantity=\"1\"/>\n" +
                "<task completionQuantity=\"1\" id=\"node_21814647-520a-4627-acd9-f02a09f81bef\" isForCompensation=\"false\" name=\"Turning &amp; Milling Q.C.\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_f9a3797f-1792-4c71-b854-e9303b19936c\" isForCompensation=\"false\" name=\"Change Version\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_9876f646-083a-4adf-bb56-81b992dcea16\" isForCompensation=\"false\" name=\"Turning\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_7bacec77-07d6-4ca1-98a7-9bc996603ba2\" isForCompensation=\"false\" name=\"Fix\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<task completionQuantity=\"1\" id=\"node_15027ffe-4494-4b1e-8562-4f856439aea4\" isForCompensation=\"false\" name=\"End\" startQuantity=\"1\">\n" +
                "<standardLoopCharacteristics testBefore=\"false\"/>\n" +
                "</task>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_2da52423-ca88-4a85-9ec1-e0011ada77cd\" name=\"\">\n" +
                "<incoming>node_09a86c4e-e327-4c3b-a68f-210717bd68ae</incoming>\n" +
                "<incoming>node_33e3a39f-8cdb-4ed1-ba10-7c16e0021d78</incoming>\n" +
                "<outgoing>node_947c1bcd-f91f-4365-8e09-e60b4484a0d8</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_490137b8-0fcd-415f-b010-613511142722\" name=\"\">\n" +
                "<incoming>node_cc19afee-0798-4146-acd5-899a2ae6588c</incoming>\n" +
                "<incoming>node_46181f82-7b20-42fa-b906-d1c39554b4f4</incoming>\n" +
                "<incoming>node_e6d2a571-6e6a-455b-9ad3-6325c4a55fa4</incoming>\n" +
                "<incoming>node_90605ce1-0739-469a-b8ca-18401f861716</incoming>\n" +
                "<outgoing>node_88382613-c6d2-40f3-b56a-d707f15cc30b</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_aac14a2e-5844-4cc9-a163-1cb1782c4ee7\" name=\"\">\n" +
                "<incoming>node_b42c7876-04fa-4d11-a14c-e472208a3222</incoming>\n" +
                "<incoming>node_091261f7-aa96-4d96-bbf0-4468c4bbcfd6</incoming>\n" +
                "<outgoing>node_e35004d1-bc85-4022-931e-7388dfd30049</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_cef22d82-b389-4576-baca-909826e3b19f\" name=\"\">\n" +
                "<incoming>node_a5735973-c10f-446e-bc4a-11082149485a</incoming>\n" +
                "<outgoing>node_e565e316-4128-4d78-8258-8ebad2b90214</outgoing>\n" +
                "<outgoing>node_f08e2097-8897-4492-9e79-61d30bb0ba6a</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_a8ec0be7-6b1a-4b82-8e2d-a8af941bf83a\" name=\"\">\n" +
                "<incoming>node_05c39224-0891-408d-9761-ef5733fc8b33</incoming>\n" +
                "<outgoing>node_f07eb11d-b451-49d4-b969-59247316c280</outgoing>\n" +
                "<outgoing>node_75f2704c-4347-418e-ac9b-813041a76886</outgoing>\n" +
                "<outgoing>node_d79e0f39-52e7-4658-a339-3bd88a963553</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_c0b23ab6-088b-4ea7-a5cc-9fee09d547b6\" name=\"\">\n" +
                "<incoming>node_632e22a6-2ac7-4c73-a62d-b7add74bd3e7</incoming>\n" +
                "<incoming>node_6416b7ae-c032-4d34-b2be-2dcf12625f59</incoming>\n" +
                "<outgoing>node_0d321e08-6e06-438a-9355-5fd4cbe31fec</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_a43e64da-2a4e-4293-8ef4-835f428f1e90\" name=\"\">\n" +
                "<incoming>node_0862d7e7-f729-473d-b79a-6f5d3628eb9f</incoming>\n" +
                "<incoming>node_3a0940fd-62a4-4af9-a2d6-ca77954f1b14</incoming>\n" +
                "<outgoing>node_3f600824-4524-4a39-b2a5-7042335a3531</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_deab82ee-19cc-4d86-b8d8-0a3310851470\" name=\"\">\n" +
                "<incoming>node_a1d00e7d-1a2d-4024-b7d3-c7fcf4557cb5</incoming>\n" +
                "<outgoing>node_f7366c69-c3dc-4f91-a555-0c38a530e10f</outgoing>\n" +
                "<outgoing>node_0d3bee4b-b9d3-4f96-86a9-d42867b8bd9f</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_6f0a8833-9e71-4193-b428-28149083c5ca\" name=\"\">\n" +
                "<incoming>node_f08e2097-8897-4492-9e79-61d30bb0ba6a</incoming>\n" +
                "<outgoing>node_f26aec31-174b-443b-a1f7-415ce556d7e2</outgoing>\n" +
                "<outgoing>node_1378e266-96ab-48fc-b1b7-d704c6559d89</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_ac0444dd-d721-41a2-8498-efd3b5129e15\" name=\"\">\n" +
                "<incoming>node_a2d7e246-246f-45df-894b-1c287a87604a</incoming>\n" +
                "<outgoing>node_506cfa9b-f3e3-4180-8bc9-8dda4dcbffe0</outgoing>\n" +
                "<outgoing>node_5d2c352a-a07b-443f-8f87-9a1c200f861f</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_4788812a-9b8d-43ef-8f1c-dcf292a29ca4\" name=\"\">\n" +
                "<incoming>node_0d3bee4b-b9d3-4f96-86a9-d42867b8bd9f</incoming>\n" +
                "<incoming>node_4894e43b-95b1-4230-939b-a3828f192807</incoming>\n" +
                "<outgoing>node_632e22a6-2ac7-4c73-a62d-b7add74bd3e7</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_4f242151-4ee8-412f-9f2c-e56bb051e90b\" name=\"\">\n" +
                "<incoming>node_00af57cc-faf6-4147-ab61-62cfbb06b2f2</incoming>\n" +
                "<outgoing>node_d6bc86f7-01ea-4e74-9589-37b246fac0cc</outgoing>\n" +
                "<outgoing>node_73926203-730d-4f00-830a-27ad36cceb3a</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_bc2014b6-53e8-4b01-bd41-abf3f44596f2\" name=\"\">\n" +
                "<incoming>node_60be9aae-9324-44d6-8913-99a70a81d62b</incoming>\n" +
                "<incoming>node_26b5128a-cf33-465c-a00a-6137d148bb34</incoming>\n" +
                "<incoming>node_55295e8c-c2e6-4a41-8110-70ac77597975</incoming>\n" +
                "<incoming>node_84758b07-b61b-4e85-8871-cc9dda087cc7</incoming>\n" +
                "<outgoing>node_24a621b0-5ac7-4309-b923-79b822ba4264</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_7f0d4728-178a-43ab-a650-5ba463fbe6d9\" name=\"\">\n" +
                "<incoming>node_5d2c352a-a07b-443f-8f87-9a1c200f861f</incoming>\n" +
                "<outgoing>node_55295e8c-c2e6-4a41-8110-70ac77597975</outgoing>\n" +
                "<outgoing>node_49cc3cff-27c9-4464-a6c4-25862189b9f3</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_7cfa0ba7-1b82-40f9-ae9a-da13be2c0089\" name=\"\">\n" +
                "<incoming>node_508a8e24-9852-4f99-9d35-a3507d43acf8</incoming>\n" +
                "<incoming>node_1240b855-c731-4f99-b6bb-e9620d3950d5</incoming>\n" +
                "<outgoing>node_a1ae54af-f9f7-4542-9cfb-77a300de5661</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_8a1da4c4-255a-4e77-a785-75cdc0b58eb1\" name=\"\">\n" +
                "<incoming>node_b8a15349-3842-4f33-a64f-cb84b2df742a</incoming>\n" +
                "<incoming>node_722d104a-2949-4f0f-8f3f-11bf3c07e809</incoming>\n" +
                "<outgoing>node_62fd347b-b14e-4ca7-856f-8f02986d9da9</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_db506519-1951-4a47-932f-ec1aed3a595d\" name=\"\">\n" +
                "<incoming>node_506cfa9b-f3e3-4180-8bc9-8dda4dcbffe0</incoming>\n" +
                "<outgoing>node_8f6615a5-c532-4d25-8638-59a8ff75dccb</outgoing>\n" +
                "<outgoing>node_ce1f5016-a951-4ecd-bc70-1612bd5b27b8</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_6cb0ad08-4eb8-4309-bb8a-d8ce7a6a0dc1\" name=\"\">\n" +
                "<incoming>node_d6bc86f7-01ea-4e74-9589-37b246fac0cc</incoming>\n" +
                "<outgoing>node_efc17f1f-3336-4a24-b736-5c30a8a53d3e</outgoing>\n" +
                "<outgoing>node_60be9aae-9324-44d6-8913-99a70a81d62b</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_c5464606-ac0e-4822-b719-ee7db03965bd\" name=\"\">\n" +
                "<incoming>node_692da630-02f0-4c7f-b16f-71e3a82a8efe</incoming>\n" +
                "<incoming>node_39fdbea4-a53d-4116-bb77-8f3677951afb</incoming>\n" +
                "<outgoing>node_e6d2a571-6e6a-455b-9ad3-6325c4a55fa4</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_16972f2f-a629-4dd9-92ae-0a7eabadf92d\" name=\"\">\n" +
                "<incoming>node_73926203-730d-4f00-830a-27ad36cceb3a</incoming>\n" +
                "<outgoing>node_091261f7-aa96-4d96-bbf0-4468c4bbcfd6</outgoing>\n" +
                "<outgoing>node_92d7e1ba-6935-4416-bcb3-6ebf4fc3b9ed</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_c05e8f47-8345-481e-8e2b-f3e9c64d86a2\" name=\"\">\n" +
                "<incoming>node_73590a40-8ce0-4bb6-8504-18bca6ca3dcf</incoming>\n" +
                "<outgoing>node_80cc656e-f020-4996-9506-87da5e40d3c0</outgoing>\n" +
                "<outgoing>node_6e0c465b-e9a6-4b62-92db-f9e59f157de5</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_38092460-848a-4444-b7de-6a1e948511ac\" name=\"\">\n" +
                "<incoming>node_ec3d72c4-f0c4-48e2-99d6-728314ddcce1</incoming>\n" +
                "<outgoing>node_62329598-43ee-4f2e-96a8-34aec385a9a6</outgoing>\n" +
                "<outgoing>node_14597095-7413-4e05-a159-8d67df98568c</outgoing>\n" +
                "<outgoing>node_508a8e24-9852-4f99-9d35-a3507d43acf8</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_c5906a0e-5fe5-4fe1-b14f-cb1e78b5923b\" name=\"\">\n" +
                "<incoming>node_5571cd6d-4c4e-42e9-860a-78ebe96ac496</incoming>\n" +
                "<incoming>node_947c1bcd-f91f-4365-8e09-e60b4484a0d8</incoming>\n" +
                "<outgoing>node_2d73c7f4-3069-4a55-80a5-fc0bcfd2b2b6</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Diverging\" id=\"node_e94f44da-2113-42ec-bac0-d7e79c2a4a0d\" name=\"\">\n" +
                "<incoming>node_ba208abe-b17a-4b09-bcaf-51ba44b8c462</incoming>\n" +
                "<outgoing>node_ccd44cbb-3a13-4940-90e7-bf7a2c467cd8</outgoing>\n" +
                "<outgoing>node_10f7037b-ebf9-4f43-ba78-fa5ab4fd0c09</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<exclusiveGateway gatewayDirection=\"Converging\" id=\"node_9e4f38ff-b0c3-4f3d-ad0b-8949f916fd4b\" name=\"\">\n" +
                "<incoming>node_61cc3ea8-45e5-4c68-8a4b-fb9ef5fb4bfd</incoming>\n" +
                "<incoming>node_88382613-c6d2-40f3-b56a-d707f15cc30b</incoming>\n" +
                "<outgoing>node_cd21f1a1-978d-4a37-ab20-28b118942dd4</outgoing>\n" +
                "</exclusiveGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_b3df6317-7937-4747-9846-b642bbc19474\" name=\"\">\n" +
                "<incoming>node_e565e316-4128-4d78-8258-8ebad2b90214</incoming>\n" +
                "<outgoing>node_4ae3227f-88fd-4c73-9aa5-7387fbdacee9</outgoing>\n" +
                "<outgoing>node_7eb10436-4073-4da2-98e6-fe6c7a880121</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_8fc6058f-e3ea-4d39-b0fd-4f7c62e542c0\" name=\"\">\n" +
                "<incoming>node_e8185eb2-07cc-4943-87bb-6edcc448cdf5</incoming>\n" +
                "<outgoing>node_a5735973-c10f-446e-bc4a-11082149485a</outgoing>\n" +
                "<outgoing>node_cc19afee-0798-4146-acd5-899a2ae6588c</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_8c2f1641-3978-4dec-8110-90b921fa7c49\" name=\"\">\n" +
                "<incoming>node_06b14ba8-7174-4bd1-a24b-fff96b9c27ee</incoming>\n" +
                "<outgoing>node_0dd4718c-7261-44ba-8964-4bbe19917b1f</outgoing>\n" +
                "<outgoing>node_93bd8ad9-152d-41e0-89db-c5c8ded1056a</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_4ba4eecd-bb10-484c-a083-33e3211e2a72\" name=\"\">\n" +
                "<incoming>node_91fa1120-1fef-4f81-9104-24467cba440c</incoming>\n" +
                "<outgoing>node_90605ce1-0739-469a-b8ca-18401f861716</outgoing>\n" +
                "<outgoing>node_1e990692-e021-42db-8c4b-789494b295ac</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_04f19bfb-7ba0-4683-b1d3-a1b543cd3d7b\" name=\"\">\n" +
                "<incoming>node_f6f91a3b-fcef-44b1-8f07-59aed9c52eb0</incoming>\n" +
                "<outgoing>node_9299f08f-92be-4340-a845-c2a4d0fe248b</outgoing>\n" +
                "<outgoing>node_ddf85e60-5613-44db-bed7-2d86ba52da30</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_17b830b7-3934-44b2-9274-024431e65a45\" name=\"\">\n" +
                "<incoming>node_b82a0b6b-a062-4f0d-8a01-6f2c77b1a8ee</incoming>\n" +
                "<outgoing>node_33e3a39f-8cdb-4ed1-ba10-7c16e0021d78</outgoing>\n" +
                "<outgoing>node_6416b7ae-c032-4d34-b2be-2dcf12625f59</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_891a754f-d31f-4b10-8ffa-f930e3d4a7e9\" name=\"\">\n" +
                "<incoming>node_21314252-26d1-40a0-936e-59690e7ee02d</incoming>\n" +
                "<outgoing>node_36936142-852c-40ff-8020-80fbf23a4cff</outgoing>\n" +
                "<outgoing>node_250f4826-8f11-4141-9151-30c814b38b74</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Unspecified\" id=\"node_47123731-f01e-4506-8969-36d326671a28\" name=\"\">\n" +
                "<incoming>node_92d7e1ba-6935-4416-bcb3-6ebf4fc3b9ed</incoming>\n" +
                "<outgoing>node_6bc97187-ab5f-4e9d-af60-576c01d81b89</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_b8cb415e-d538-489f-845f-1ac0ca724c96\" name=\"\">\n" +
                "<incoming>node_ef929865-0f60-4cc1-80f6-9080adc93de3</incoming>\n" +
                "<outgoing>node_00800efc-062d-493a-be60-b03ced9f7c82</outgoing>\n" +
                "<outgoing>node_f6f91a3b-fcef-44b1-8f07-59aed9c52eb0</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_30ef7ddd-78ca-47a7-b0c6-dbed5397df67\" name=\"\">\n" +
                "<incoming>node_2034ce92-bab4-4a91-b509-eefd781c50ad</incoming>\n" +
                "<outgoing>node_b82a0b6b-a062-4f0d-8a01-6f2c77b1a8ee</outgoing>\n" +
                "<outgoing>node_06b14ba8-7174-4bd1-a24b-fff96b9c27ee</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Unspecified\" id=\"node_5ca6dd62-e695-4e8e-bbbf-0fc736dded9d\" name=\"\">\n" +
                "<incoming>node_f26aec31-174b-443b-a1f7-415ce556d7e2</incoming>\n" +
                "<outgoing>node_7c6d5c33-fa7a-45ff-82ac-8c6857dcc386</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_92ede154-e501-4ae1-9dd7-18e1d0125fed\" name=\"\">\n" +
                "<incoming>node_250f4826-8f11-4141-9151-30c814b38b74</incoming>\n" +
                "<outgoing>node_e4892bef-ea24-4db4-9752-b50ec01dad49</outgoing>\n" +
                "<outgoing>node_ef929865-0f60-4cc1-80f6-9080adc93de3</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_dc9f88f1-ec9c-4d54-8047-60d652a2601b\" name=\"\">\n" +
                "<incoming>node_36936142-852c-40ff-8020-80fbf23a4cff</incoming>\n" +
                "<outgoing>node_05c39224-0891-408d-9761-ef5733fc8b33</outgoing>\n" +
                "<outgoing>node_cd316582-a406-41e8-b65a-0d9fbbf19285</outgoing>\n" +
                "</parallelGateway>\n" +
                "<parallelGateway gatewayDirection=\"Diverging\" id=\"node_bda9b1f0-7e67-4f50-b062-db435ba49bd5\" name=\"\">\n" +
                "<incoming>node_9299f08f-92be-4340-a845-c2a4d0fe248b</incoming>\n" +
                "<outgoing>node_91fa1120-1fef-4f81-9104-24467cba440c</outgoing>\n" +
                "<outgoing>node_2034ce92-bab4-4a91-b509-eefd781c50ad</outgoing>\n" +
                "</parallelGateway>\n" +
                "<inclusiveGateway gatewayDirection=\"Converging\" id=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\" name=\"\">\n" +
                "<incoming>node_6e0c465b-e9a6-4b62-92db-f9e59f157de5</incoming>\n" +
                "<incoming>node_a1ae54af-f9f7-4542-9cfb-77a300de5661</incoming>\n" +
                "<incoming>node_8f6615a5-c532-4d25-8638-59a8ff75dccb</incoming>\n" +
                "<incoming>node_ccd44cbb-3a13-4940-90e7-bf7a2c467cd8</incoming>\n" +
                "<incoming>node_5826a6f6-e745-44e5-a4aa-89482d4b48a3</incoming>\n" +
                "<incoming>node_7c6d5c33-fa7a-45ff-82ac-8c6857dcc386</incoming>\n" +
                "<incoming>node_ea1d3200-32e6-42ed-a99f-d8981dd62abf</incoming>\n" +
                "<outgoing>node_b42c7876-04fa-4d11-a14c-e472208a3222</outgoing>\n" +
                "</inclusiveGateway>\n" +
                "<inclusiveGateway gatewayDirection=\"Converging\" id=\"node_5110d3a0-12bc-4def-b75e-67a144e6c920\" name=\"\">\n" +
                "<incoming>node_eed95410-9566-4d06-8283-5d9590fa0108</incoming>\n" +
                "<incoming>node_00800efc-062d-493a-be60-b03ced9f7c82</incoming>\n" +
                "<incoming>node_1dc69fb5-da4c-4522-91d2-899ea3f96d01</incoming>\n" +
                "<outgoing>node_26b5128a-cf33-465c-a00a-6137d148bb34</outgoing>\n" +
                "</inclusiveGateway>\n" +
                "<inclusiveGateway gatewayDirection=\"Converging\" id=\"node_1606f7e8-6a4d-4ea1-ae1d-bf9a26d3a754\" name=\"\">\n" +
                "<incoming>node_6bc97187-ab5f-4e9d-af60-576c01d81b89</incoming>\n" +
                "<incoming>node_2cd7e2e9-6ee7-4a05-957b-6efa428cbcb2</incoming>\n" +
                "<outgoing>node_73dfa413-faa7-48b9-96e4-f781b0244d08</outgoing>\n" +
                "</inclusiveGateway>\n" +
                "<inclusiveGateway gatewayDirection=\"Converging\" id=\"node_9a473d33-25e1-407a-b971-1d080a43ea49\" name=\"\">\n" +
                "<incoming>node_7eb10436-4073-4da2-98e6-fe6c7a880121</incoming>\n" +
                "<incoming>node_1e990692-e021-42db-8c4b-789494b295ac</incoming>\n" +
                "<outgoing>node_b8a15349-3842-4f33-a64f-cb84b2df742a</outgoing>\n" +
                "</inclusiveGateway>\n" +
                "<inclusiveGateway gatewayDirection=\"Converging\" id=\"node_dad6cdda-6fac-4b2f-b0f0-ed84d6c5b144\" name=\"\">\n" +
                "<incoming>node_93bd8ad9-152d-41e0-89db-c5c8ded1056a</incoming>\n" +
                "<incoming>node_1f87a21d-05e2-44a8-bc02-10d102f8aaf8</incoming>\n" +
                "<outgoing>node_74347c1e-e0fe-4433-aae4-eaec96e2e8f2</outgoing>\n" +
                "</inclusiveGateway>\n" +
                "<sequenceFlow id=\"node_9e9f7266-73cf-4b52-b131-9be93e0def13\" name=\"\" sourceRef=\"node_164f56bc-12ca-43ec-823f-a5f481dc2a0d\" targetRef=\"node_e93bf2b2-8903-43cf-928e-382d1ecb1bf9\"/>\n" +
                "<sequenceFlow id=\"node_21314252-26d1-40a0-936e-59690e7ee02d\" name=\"\" sourceRef=\"node_e93bf2b2-8903-43cf-928e-382d1ecb1bf9\" targetRef=\"node_891a754f-d31f-4b10-8ffa-f930e3d4a7e9\"/>\n" +
                "<sequenceFlow id=\"node_36936142-852c-40ff-8020-80fbf23a4cff\" name=\"\" sourceRef=\"node_891a754f-d31f-4b10-8ffa-f930e3d4a7e9\" targetRef=\"node_dc9f88f1-ec9c-4d54-8047-60d652a2601b\"/>\n" +
                "<sequenceFlow id=\"node_cd316582-a406-41e8-b65a-0d9fbbf19285\" name=\"\" sourceRef=\"node_dc9f88f1-ec9c-4d54-8047-60d652a2601b\" targetRef=\"node_b0aeb4d1-63bc-440f-8acd-c8182929aaed\"/>\n" +
                "<sequenceFlow id=\"node_05c39224-0891-408d-9761-ef5733fc8b33\" name=\"\" sourceRef=\"node_dc9f88f1-ec9c-4d54-8047-60d652a2601b\" targetRef=\"node_a8ec0be7-6b1a-4b82-8e2d-a8af941bf83a\"/>\n" +
                "<sequenceFlow id=\"node_75f2704c-4347-418e-ac9b-813041a76886\" name=\"\" sourceRef=\"node_a8ec0be7-6b1a-4b82-8e2d-a8af941bf83a\" targetRef=\"node_f9a3797f-1792-4c71-b854-e9303b19936c\"/>\n" +
                "<sequenceFlow id=\"node_d79e0f39-52e7-4658-a339-3bd88a963553\" name=\"\" sourceRef=\"node_a8ec0be7-6b1a-4b82-8e2d-a8af941bf83a\" targetRef=\"node_f9c6a777-9756-46ec-8654-a7768dc75888\"/>\n" +
                "<sequenceFlow id=\"node_f07eb11d-b451-49d4-b969-59247316c280\" name=\"\" sourceRef=\"node_a8ec0be7-6b1a-4b82-8e2d-a8af941bf83a\" targetRef=\"node_0af32f51-93a5-4bcd-a7dd-e0fef53b6cd0\"/>\n" +
                "<sequenceFlow id=\"node_250f4826-8f11-4141-9151-30c814b38b74\" name=\"\" sourceRef=\"node_891a754f-d31f-4b10-8ffa-f930e3d4a7e9\" targetRef=\"node_92ede154-e501-4ae1-9dd7-18e1d0125fed\"/>\n" +
                "<sequenceFlow id=\"node_e4892bef-ea24-4db4-9752-b50ec01dad49\" name=\"\" sourceRef=\"node_92ede154-e501-4ae1-9dd7-18e1d0125fed\" targetRef=\"node_d38b3dc4-5411-4579-9b6b-31463d34358a\"/>\n" +
                "<sequenceFlow id=\"node_ef929865-0f60-4cc1-80f6-9080adc93de3\" name=\"\" sourceRef=\"node_92ede154-e501-4ae1-9dd7-18e1d0125fed\" targetRef=\"node_b8cb415e-d538-489f-845f-1ac0ca724c96\"/>\n" +
                "<sequenceFlow id=\"node_f6f91a3b-fcef-44b1-8f07-59aed9c52eb0\" name=\"\" sourceRef=\"node_b8cb415e-d538-489f-845f-1ac0ca724c96\" targetRef=\"node_04f19bfb-7ba0-4683-b1d3-a1b543cd3d7b\"/>\n" +
                "<sequenceFlow id=\"node_ddf85e60-5613-44db-bed7-2d86ba52da30\" name=\"\" sourceRef=\"node_04f19bfb-7ba0-4683-b1d3-a1b543cd3d7b\" targetRef=\"node_76d9f547-7067-453e-ad07-0006156501cb\"/>\n" +
                "<sequenceFlow id=\"node_9299f08f-92be-4340-a845-c2a4d0fe248b\" name=\"\" sourceRef=\"node_04f19bfb-7ba0-4683-b1d3-a1b543cd3d7b\" targetRef=\"node_bda9b1f0-7e67-4f50-b062-db435ba49bd5\"/>\n" +
                "<sequenceFlow id=\"node_2034ce92-bab4-4a91-b509-eefd781c50ad\" name=\"\" sourceRef=\"node_bda9b1f0-7e67-4f50-b062-db435ba49bd5\" targetRef=\"node_30ef7ddd-78ca-47a7-b0c6-dbed5397df67\"/>\n" +
                "<sequenceFlow id=\"node_06b14ba8-7174-4bd1-a24b-fff96b9c27ee\" name=\"\" sourceRef=\"node_30ef7ddd-78ca-47a7-b0c6-dbed5397df67\" targetRef=\"node_8c2f1641-3978-4dec-8110-90b921fa7c49\"/>\n" +
                "<sequenceFlow id=\"node_0dd4718c-7261-44ba-8964-4bbe19917b1f\" name=\"\" sourceRef=\"node_8c2f1641-3978-4dec-8110-90b921fa7c49\" targetRef=\"node_9876f646-083a-4adf-bb56-81b992dcea16\"/>\n" +
                "<sequenceFlow id=\"node_b82a0b6b-a062-4f0d-8a01-6f2c77b1a8ee\" name=\"\" sourceRef=\"node_30ef7ddd-78ca-47a7-b0c6-dbed5397df67\" targetRef=\"node_17b830b7-3934-44b2-9274-024431e65a45\"/>\n" +
                "<sequenceFlow id=\"node_91fa1120-1fef-4f81-9104-24467cba440c\" name=\"\" sourceRef=\"node_bda9b1f0-7e67-4f50-b062-db435ba49bd5\" targetRef=\"node_4ba4eecd-bb10-484c-a083-33e3211e2a72\"/>\n" +
                "<sequenceFlow id=\"node_73590a40-8ce0-4bb6-8504-18bca6ca3dcf\" name=\"\" sourceRef=\"node_9d0fee08-a6a8-46a0-8305-7d50a7577efb\" targetRef=\"node_c05e8f47-8345-481e-8e2b-f3e9c64d86a2\"/>\n" +
                "<sequenceFlow id=\"node_80cc656e-f020-4996-9506-87da5e40d3c0\" name=\"\" sourceRef=\"node_c05e8f47-8345-481e-8e2b-f3e9c64d86a2\" targetRef=\"node_b2e0b726-30f5-43e9-89c4-041d593ae23a\"/>\n" +
                "<sequenceFlow id=\"node_e8185eb2-07cc-4943-87bb-6edcc448cdf5\" name=\"\" sourceRef=\"node_21814647-520a-4627-acd9-f02a09f81bef\" targetRef=\"node_8fc6058f-e3ea-4d39-b0fd-4f7c62e542c0\"/>\n" +
                "<sequenceFlow id=\"node_a5735973-c10f-446e-bc4a-11082149485a\" name=\"\" sourceRef=\"node_8fc6058f-e3ea-4d39-b0fd-4f7c62e542c0\" targetRef=\"node_cef22d82-b389-4576-baca-909826e3b19f\"/>\n" +
                "<sequenceFlow id=\"node_e565e316-4128-4d78-8258-8ebad2b90214\" name=\"\" sourceRef=\"node_cef22d82-b389-4576-baca-909826e3b19f\" targetRef=\"node_b3df6317-7937-4747-9846-b642bbc19474\"/>\n" +
                "<sequenceFlow id=\"node_4ae3227f-88fd-4c73-9aa5-7387fbdacee9\" name=\"\" sourceRef=\"node_b3df6317-7937-4747-9846-b642bbc19474\" targetRef=\"node_545cde46-c830-4c30-839a-b400a652c54a\"/>\n" +
                "<sequenceFlow id=\"node_ec3d72c4-f0c4-48e2-99d6-728314ddcce1\" name=\"\" sourceRef=\"node_a2398cb6-3eda-4b5e-9913-71d01438c4e8\" targetRef=\"node_38092460-848a-4444-b7de-6a1e948511ac\"/>\n" +
                "<sequenceFlow id=\"node_62329598-43ee-4f2e-96a8-34aec385a9a6\" name=\"\" sourceRef=\"node_38092460-848a-4444-b7de-6a1e948511ac\" targetRef=\"node_7bacec77-07d6-4ca1-98a7-9bc996603ba2\"/>\n" +
                "<sequenceFlow id=\"node_14597095-7413-4e05-a159-8d67df98568c\" name=\"\" sourceRef=\"node_38092460-848a-4444-b7de-6a1e948511ac\" targetRef=\"node_7df46f9e-6a9a-4d55-bdc9-8c361c9fefba\"/>\n" +
                "<sequenceFlow id=\"node_00af57cc-faf6-4147-ab61-62cfbb06b2f2\" name=\"\" sourceRef=\"node_f52acf4e-eb1f-4edf-98d7-e09e89874ab3\" targetRef=\"node_4f242151-4ee8-412f-9f2c-e56bb051e90b\"/>\n" +
                "<sequenceFlow id=\"node_d6bc86f7-01ea-4e74-9589-37b246fac0cc\" name=\"\" sourceRef=\"node_4f242151-4ee8-412f-9f2c-e56bb051e90b\" targetRef=\"node_6cb0ad08-4eb8-4309-bb8a-d8ce7a6a0dc1\"/>\n" +
                "<sequenceFlow id=\"node_efc17f1f-3336-4a24-b736-5c30a8a53d3e\" name=\"\" sourceRef=\"node_6cb0ad08-4eb8-4309-bb8a-d8ce7a6a0dc1\" targetRef=\"node_ed81b338-0b45-44e3-834f-db859b37b128\"/>\n" +
                "<sequenceFlow id=\"node_a2d7e246-246f-45df-894b-1c287a87604a\" name=\"\" sourceRef=\"node_358a5db3-c523-4268-8c88-42674ed2bc0a\" targetRef=\"node_ac0444dd-d721-41a2-8498-efd3b5129e15\"/>\n" +
                "<sequenceFlow id=\"node_5d2c352a-a07b-443f-8f87-9a1c200f861f\" name=\"\" sourceRef=\"node_ac0444dd-d721-41a2-8498-efd3b5129e15\" targetRef=\"node_7f0d4728-178a-43ab-a650-5ba463fbe6d9\"/>\n" +
                "<sequenceFlow id=\"node_49cc3cff-27c9-4464-a6c4-25862189b9f3\" name=\"\" sourceRef=\"node_7f0d4728-178a-43ab-a650-5ba463fbe6d9\" targetRef=\"node_844fbeda-0c8e-481c-90e2-dd7f95097134\"/>\n" +
                "<sequenceFlow id=\"node_506cfa9b-f3e3-4180-8bc9-8dda4dcbffe0\" name=\"\" sourceRef=\"node_ac0444dd-d721-41a2-8498-efd3b5129e15\" targetRef=\"node_db506519-1951-4a47-932f-ec1aed3a595d\"/>\n" +
                "<sequenceFlow id=\"node_ce1f5016-a951-4ecd-bc70-1612bd5b27b8\" name=\"\" sourceRef=\"node_db506519-1951-4a47-932f-ec1aed3a595d\" targetRef=\"node_fb0462da-8de5-41fc-bd0e-408279cbc38a\"/>\n" +
                "<sequenceFlow id=\"node_a1d00e7d-1a2d-4024-b7d3-c7fcf4557cb5\" name=\"\" sourceRef=\"node_b2e0b726-30f5-43e9-89c4-041d593ae23a\" targetRef=\"node_deab82ee-19cc-4d86-b8d8-0a3310851470\"/>\n" +
                "<sequenceFlow id=\"node_f7366c69-c3dc-4f91-a555-0c38a530e10f\" name=\"\" sourceRef=\"node_deab82ee-19cc-4d86-b8d8-0a3310851470\" targetRef=\"node_ade5e46b-fb90-4839-8f80-dc20feefca2b\"/>\n" +
                "<sequenceFlow id=\"node_ba208abe-b17a-4b09-bcaf-51ba44b8c462\" name=\"\" sourceRef=\"node_1dcc87ee-08b7-4aaf-a756-e8f489e373f1\" targetRef=\"node_e94f44da-2113-42ec-bac0-d7e79c2a4a0d\"/>\n" +
                "<sequenceFlow id=\"node_10f7037b-ebf9-4f43-ba78-fa5ab4fd0c09\" name=\"\" sourceRef=\"node_e94f44da-2113-42ec-bac0-d7e79c2a4a0d\" targetRef=\"node_fafb8a71-1f14-4f99-a37b-ce93b98d94b6\"/>\n" +
                "<sequenceFlow id=\"node_0d3bee4b-b9d3-4f96-86a9-d42867b8bd9f\" name=\"\" sourceRef=\"node_deab82ee-19cc-4d86-b8d8-0a3310851470\" targetRef=\"node_4788812a-9b8d-43ef-8f1c-dcf292a29ca4\"/>\n" +
                "<sequenceFlow id=\"node_4894e43b-95b1-4230-939b-a3828f192807\" name=\"\" sourceRef=\"node_ade5e46b-fb90-4839-8f80-dc20feefca2b\" targetRef=\"node_4788812a-9b8d-43ef-8f1c-dcf292a29ca4\"/>\n" +
                "<sequenceFlow id=\"node_692da630-02f0-4c7f-b16f-71e3a82a8efe\" name=\"\" sourceRef=\"node_f9c6a777-9756-46ec-8654-a7768dc75888\" targetRef=\"node_c5464606-ac0e-4822-b719-ee7db03965bd\"/>\n" +
                "<sequenceFlow id=\"node_39fdbea4-a53d-4116-bb77-8f3677951afb\" name=\"\" sourceRef=\"node_0af32f51-93a5-4bcd-a7dd-e0fef53b6cd0\" targetRef=\"node_c5464606-ac0e-4822-b719-ee7db03965bd\"/>\n" +
                "<sequenceFlow id=\"node_1240b855-c731-4f99-b6bb-e9620d3950d5\" name=\"\" sourceRef=\"node_7df46f9e-6a9a-4d55-bdc9-8c361c9fefba\" targetRef=\"node_7cfa0ba7-1b82-40f9-ae9a-da13be2c0089\"/>\n" +
                "<sequenceFlow id=\"node_508a8e24-9852-4f99-9d35-a3507d43acf8\" name=\"\" sourceRef=\"node_38092460-848a-4444-b7de-6a1e948511ac\" targetRef=\"node_7cfa0ba7-1b82-40f9-ae9a-da13be2c0089\"/>\n" +
                "<sequenceFlow id=\"node_73dfa413-faa7-48b9-96e4-f781b0244d08\" name=\"\" sourceRef=\"node_1606f7e8-6a4d-4ea1-ae1d-bf9a26d3a754\" targetRef=\"node_10fc9b99-5bd8-4878-b043-0de983821211\"/>\n" +
                "<sequenceFlow id=\"node_0d321e08-6e06-438a-9355-5fd4cbe31fec\" name=\"\" sourceRef=\"node_c0b23ab6-088b-4ea7-a5cc-9fee09d547b6\" targetRef=\"node_9d0fee08-a6a8-46a0-8305-7d50a7577efb\"/>\n" +
                "<sequenceFlow id=\"node_632e22a6-2ac7-4c73-a62d-b7add74bd3e7\" name=\"\" sourceRef=\"node_4788812a-9b8d-43ef-8f1c-dcf292a29ca4\" targetRef=\"node_c0b23ab6-088b-4ea7-a5cc-9fee09d547b6\"/>\n" +
                "<sequenceFlow id=\"node_6416b7ae-c032-4d34-b2be-2dcf12625f59\" name=\"\" sourceRef=\"node_17b830b7-3934-44b2-9274-024431e65a45\" targetRef=\"node_c0b23ab6-088b-4ea7-a5cc-9fee09d547b6\"/>\n" +
                "<sequenceFlow id=\"node_3f600824-4524-4a39-b2a5-7042335a3531\" name=\"\" sourceRef=\"node_a43e64da-2a4e-4293-8ef4-835f428f1e90\" targetRef=\"node_1dcc87ee-08b7-4aaf-a756-e8f489e373f1\"/>\n" +
                "<sequenceFlow id=\"node_74347c1e-e0fe-4433-aae4-eaec96e2e8f2\" name=\"\" sourceRef=\"node_dad6cdda-6fac-4b2f-b0f0-ed84d6c5b144\" targetRef=\"node_ce118ed3-7b12-42c9-82ea-55f896617ef2\"/>\n" +
                "<sequenceFlow id=\"node_e35004d1-bc85-4022-931e-7388dfd30049\" name=\"\" sourceRef=\"node_aac14a2e-5844-4cc9-a163-1cb1782c4ee7\" targetRef=\"node_15027ffe-4494-4b1e-8562-4f856439aea4\"/>\n" +
                "<sequenceFlow id=\"node_b42c7876-04fa-4d11-a14c-e472208a3222\" name=\"\" sourceRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\" targetRef=\"node_aac14a2e-5844-4cc9-a163-1cb1782c4ee7\"/>\n" +
                "<sequenceFlow id=\"node_2d73c7f4-3069-4a55-80a5-fc0bcfd2b2b6\" name=\"\" sourceRef=\"node_c5906a0e-5fe5-4fe1-b14f-cb1e78b5923b\" targetRef=\"node_a2398cb6-3eda-4b5e-9913-71d01438c4e8\"/>\n" +
                "<sequenceFlow id=\"node_947c1bcd-f91f-4365-8e09-e60b4484a0d8\" name=\"\" sourceRef=\"node_2da52423-ca88-4a85-9ec1-e0011ada77cd\" targetRef=\"node_c5906a0e-5fe5-4fe1-b14f-cb1e78b5923b\"/>\n" +
                "<sequenceFlow id=\"node_5571cd6d-4c4e-42e9-860a-78ebe96ac496\" name=\"\" sourceRef=\"node_fb0462da-8de5-41fc-bd0e-408279cbc38a\" targetRef=\"node_c5906a0e-5fe5-4fe1-b14f-cb1e78b5923b\"/>\n" +
                "<sequenceFlow id=\"node_cd21f1a1-978d-4a37-ab20-28b118942dd4\" name=\"\" sourceRef=\"node_9e4f38ff-b0c3-4f3d-ad0b-8949f916fd4b\" targetRef=\"node_f52acf4e-eb1f-4edf-98d7-e09e89874ab3\"/>\n" +
                "<sequenceFlow id=\"node_88382613-c6d2-40f3-b56a-d707f15cc30b\" name=\"\" sourceRef=\"node_490137b8-0fcd-415f-b010-613511142722\" targetRef=\"node_9e4f38ff-b0c3-4f3d-ad0b-8949f916fd4b\"/>\n" +
                "<sequenceFlow id=\"node_61cc3ea8-45e5-4c68-8a4b-fb9ef5fb4bfd\" name=\"\" sourceRef=\"node_ed81b338-0b45-44e3-834f-db859b37b128\" targetRef=\"node_9e4f38ff-b0c3-4f3d-ad0b-8949f916fd4b\"/>\n" +
                "<sequenceFlow id=\"node_24a621b0-5ac7-4309-b923-79b822ba4264\" name=\"\" sourceRef=\"node_bc2014b6-53e8-4b01-bd41-abf3f44596f2\" targetRef=\"node_21814647-520a-4627-acd9-f02a09f81bef\"/>\n" +
                "<sequenceFlow id=\"node_26b5128a-cf33-465c-a00a-6137d148bb34\" name=\"\" sourceRef=\"node_5110d3a0-12bc-4def-b75e-67a144e6c920\" targetRef=\"node_bc2014b6-53e8-4b01-bd41-abf3f44596f2\"/>\n" +
                "<sequenceFlow id=\"node_55295e8c-c2e6-4a41-8110-70ac77597975\" name=\"\" sourceRef=\"node_7f0d4728-178a-43ab-a650-5ba463fbe6d9\" targetRef=\"node_bc2014b6-53e8-4b01-bd41-abf3f44596f2\"/>\n" +
                "<sequenceFlow id=\"node_84758b07-b61b-4e85-8871-cc9dda087cc7\" name=\"\" sourceRef=\"node_6fc429a9-7695-446f-92f2-937e854125a5\" targetRef=\"node_bc2014b6-53e8-4b01-bd41-abf3f44596f2\"/>\n" +
                "<sequenceFlow id=\"node_60be9aae-9324-44d6-8913-99a70a81d62b\" name=\"\" sourceRef=\"node_6cb0ad08-4eb8-4309-bb8a-d8ce7a6a0dc1\" targetRef=\"node_bc2014b6-53e8-4b01-bd41-abf3f44596f2\"/>\n" +
                "<sequenceFlow id=\"node_62fd347b-b14e-4ca7-856f-8f02986d9da9\" name=\"\" sourceRef=\"node_8a1da4c4-255a-4e77-a785-75cdc0b58eb1\" targetRef=\"node_358a5db3-c523-4268-8c88-42674ed2bc0a\"/>\n" +
                "<sequenceFlow id=\"node_b8a15349-3842-4f33-a64f-cb84b2df742a\" name=\"\" sourceRef=\"node_9a473d33-25e1-407a-b971-1d080a43ea49\" targetRef=\"node_8a1da4c4-255a-4e77-a785-75cdc0b58eb1\"/>\n" +
                "<sequenceFlow id=\"node_722d104a-2949-4f0f-8f3f-11bf3c07e809\" name=\"\" sourceRef=\"node_844fbeda-0c8e-481c-90e2-dd7f95097134\" targetRef=\"node_8a1da4c4-255a-4e77-a785-75cdc0b58eb1\"/>\n" +
                "<sequenceFlow id=\"node_7c6d5c33-fa7a-45ff-82ac-8c6857dcc386\" name=\"\" sourceRef=\"node_5ca6dd62-e695-4e8e-bbbf-0fc736dded9d\" targetRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\"/>\n" +
                "<sequenceFlow id=\"node_1f87a21d-05e2-44a8-bc02-10d102f8aaf8\" name=\"\" sourceRef=\"node_545cde46-c830-4c30-839a-b400a652c54a\" targetRef=\"node_dad6cdda-6fac-4b2f-b0f0-ed84d6c5b144\"/>\n" +
                "<sequenceFlow id=\"node_e6d2a571-6e6a-455b-9ad3-6325c4a55fa4\" name=\"\" sourceRef=\"node_c5464606-ac0e-4822-b719-ee7db03965bd\" targetRef=\"node_490137b8-0fcd-415f-b010-613511142722\"/>\n" +
                "<sequenceFlow id=\"node_6e0c465b-e9a6-4b62-92db-f9e59f157de5\" name=\"\" sourceRef=\"node_c05e8f47-8345-481e-8e2b-f3e9c64d86a2\" targetRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\"/>\n" +
                "<sequenceFlow id=\"node_5826a6f6-e745-44e5-a4aa-89482d4b48a3\" name=\"\" sourceRef=\"node_9876f646-083a-4adf-bb56-81b992dcea16\" targetRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\"/>\n" +
                "<sequenceFlow id=\"node_6bc97187-ab5f-4e9d-af60-576c01d81b89\" name=\"\" sourceRef=\"node_47123731-f01e-4506-8969-36d326671a28\" targetRef=\"node_1606f7e8-6a4d-4ea1-ae1d-bf9a26d3a754\"/>\n" +
                "<sequenceFlow id=\"node_00800efc-062d-493a-be60-b03ced9f7c82\" name=\"\" sourceRef=\"node_b8cb415e-d538-489f-845f-1ac0ca724c96\" targetRef=\"node_5110d3a0-12bc-4def-b75e-67a144e6c920\"/>\n" +
                "<sequenceFlow id=\"node_8f6615a5-c532-4d25-8638-59a8ff75dccb\" name=\"\" sourceRef=\"node_db506519-1951-4a47-932f-ec1aed3a595d\" targetRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\"/>\n" +
                "<sequenceFlow id=\"node_a1ae54af-f9f7-4542-9cfb-77a300de5661\" name=\"\" sourceRef=\"node_7cfa0ba7-1b82-40f9-ae9a-da13be2c0089\" targetRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\"/>\n" +
                "<sequenceFlow id=\"node_ccd44cbb-3a13-4940-90e7-bf7a2c467cd8\" name=\"\" sourceRef=\"node_e94f44da-2113-42ec-bac0-d7e79c2a4a0d\" targetRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\"/>\n" +
                "<sequenceFlow id=\"node_1dc69fb5-da4c-4522-91d2-899ea3f96d01\" name=\"\" sourceRef=\"node_7bacec77-07d6-4ca1-98a7-9bc996603ba2\" targetRef=\"node_5110d3a0-12bc-4def-b75e-67a144e6c920\"/>\n" +
                "<sequenceFlow id=\"node_93bd8ad9-152d-41e0-89db-c5c8ded1056a\" name=\"\" sourceRef=\"node_8c2f1641-3978-4dec-8110-90b921fa7c49\" targetRef=\"node_dad6cdda-6fac-4b2f-b0f0-ed84d6c5b144\"/>\n" +
                "<sequenceFlow id=\"node_90605ce1-0739-469a-b8ca-18401f861716\" name=\"\" sourceRef=\"node_4ba4eecd-bb10-484c-a083-33e3211e2a72\" targetRef=\"node_490137b8-0fcd-415f-b010-613511142722\"/>\n" +
                "<sequenceFlow id=\"node_46181f82-7b20-42fa-b906-d1c39554b4f4\" name=\"\" sourceRef=\"node_15027ffe-4494-4b1e-8562-4f856439aea4\" targetRef=\"node_490137b8-0fcd-415f-b010-613511142722\"/>\n" +
                "<sequenceFlow id=\"node_33e3a39f-8cdb-4ed1-ba10-7c16e0021d78\" name=\"\" sourceRef=\"node_17b830b7-3934-44b2-9274-024431e65a45\" targetRef=\"node_2da52423-ca88-4a85-9ec1-e0011ada77cd\"/>\n" +
                "<sequenceFlow id=\"node_cc19afee-0798-4146-acd5-899a2ae6588c\" name=\"\" sourceRef=\"node_8fc6058f-e3ea-4d39-b0fd-4f7c62e542c0\" targetRef=\"node_490137b8-0fcd-415f-b010-613511142722\"/>\n" +
                "<sequenceFlow id=\"node_1e990692-e021-42db-8c4b-789494b295ac\" name=\"\" sourceRef=\"node_4ba4eecd-bb10-484c-a083-33e3211e2a72\" targetRef=\"node_9a473d33-25e1-407a-b971-1d080a43ea49\"/>\n" +
                "<sequenceFlow id=\"node_0862d7e7-f729-473d-b79a-6f5d3628eb9f\" name=\"\" sourceRef=\"node_f9a3797f-1792-4c71-b854-e9303b19936c\" targetRef=\"node_a43e64da-2a4e-4293-8ef4-835f428f1e90\"/>\n" +
                "<sequenceFlow id=\"node_3a0940fd-62a4-4af9-a2d6-ca77954f1b14\" name=\"\" sourceRef=\"node_b0aeb4d1-63bc-440f-8acd-c8182929aaed\" targetRef=\"node_a43e64da-2a4e-4293-8ef4-835f428f1e90\"/>\n" +
                "<sequenceFlow id=\"node_eed95410-9566-4d06-8283-5d9590fa0108\" name=\"\" sourceRef=\"node_d38b3dc4-5411-4579-9b6b-31463d34358a\" targetRef=\"node_5110d3a0-12bc-4def-b75e-67a144e6c920\"/>\n" +
                "<sequenceFlow id=\"node_ea1d3200-32e6-42ed-a99f-d8981dd62abf\" name=\"\" sourceRef=\"node_ce118ed3-7b12-42c9-82ea-55f896617ef2\" targetRef=\"node_744f6664-afa7-4dad-ab39-d38d1b8fdffe\"/>\n" +
                "<sequenceFlow id=\"node_2cd7e2e9-6ee7-4a05-957b-6efa428cbcb2\" name=\"\" sourceRef=\"node_76d9f547-7067-453e-ad07-0006156501cb\" targetRef=\"node_1606f7e8-6a4d-4ea1-ae1d-bf9a26d3a754\"/>\n" +
                "<sequenceFlow id=\"node_7eb10436-4073-4da2-98e6-fe6c7a880121\" name=\"\" sourceRef=\"node_b3df6317-7937-4747-9846-b642bbc19474\" targetRef=\"node_9a473d33-25e1-407a-b971-1d080a43ea49\"/>\n" +
                "<sequenceFlow id=\"node_09a86c4e-e327-4c3b-a68f-210717bd68ae\" name=\"\" sourceRef=\"node_fafb8a71-1f14-4f99-a37b-ce93b98d94b6\" targetRef=\"node_2da52423-ca88-4a85-9ec1-e0011ada77cd\"/>\n" +
                "<sequenceFlow id=\"node_f08e2097-8897-4492-9e79-61d30bb0ba6a\" name=\"\" sourceRef=\"node_cef22d82-b389-4576-baca-909826e3b19f\" targetRef=\"node_6f0a8833-9e71-4193-b428-28149083c5ca\"/>\n" +
                "<sequenceFlow id=\"node_f26aec31-174b-443b-a1f7-415ce556d7e2\" name=\"\" sourceRef=\"node_6f0a8833-9e71-4193-b428-28149083c5ca\" targetRef=\"node_5ca6dd62-e695-4e8e-bbbf-0fc736dded9d\"/>\n" +
                "<sequenceFlow id=\"node_1378e266-96ab-48fc-b1b7-d704c6559d89\" name=\"\" sourceRef=\"node_6f0a8833-9e71-4193-b428-28149083c5ca\" targetRef=\"node_6fc429a9-7695-446f-92f2-937e854125a5\"/>\n" +
                "<sequenceFlow id=\"node_73926203-730d-4f00-830a-27ad36cceb3a\" name=\"\" sourceRef=\"node_4f242151-4ee8-412f-9f2c-e56bb051e90b\" targetRef=\"node_16972f2f-a629-4dd9-92ae-0a7eabadf92d\"/>\n" +
                "<sequenceFlow id=\"node_92d7e1ba-6935-4416-bcb3-6ebf4fc3b9ed\" name=\"\" sourceRef=\"node_16972f2f-a629-4dd9-92ae-0a7eabadf92d\" targetRef=\"node_47123731-f01e-4506-8969-36d326671a28\"/>\n" +
                "<sequenceFlow id=\"node_091261f7-aa96-4d96-bbf0-4468c4bbcfd6\" name=\"\" sourceRef=\"node_16972f2f-a629-4dd9-92ae-0a7eabadf92d\" targetRef=\"node_aac14a2e-5844-4cc9-a163-1cb1782c4ee7\"/>\n" +
                "</process>\n" +
                "</definitions>\n";
        String result = (new BPMNLayoutCreatorCamunda()).createLayout(bpmnModel);
        System.out.println(result);
        assertTrue(result.contains("BPMNShape"));
    }

}
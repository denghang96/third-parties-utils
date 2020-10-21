package com.dengooo.alipay;

import com.dengooo.alipay.config.AliPayConfig;
import com.dengooo.alipay.pay.AliScanPayUtils;
import com.dengooo.alipay.pay.PrecreateReqParam;

public class TestMain {
    public static void main(String[] args) {
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAliPayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgVqzpeDKEphsVoE9SjBnJ9/esrOzg/I5ys3OTONeH18DCi2Ohaik8oU0LppixQkYzw1trChVleru9f0ttT/JSiq6pIVYpvmby7VrCnuXE58MFD0G3cNvSv3ebS5fikEjpD/+XXqtXQpWfqCgsDmQ5VVuZIXpBzqYNGaaiTD1BHzYn0gPNcUcvT4Tc+6oO1PRScTKp4C1bCNng1YxwM6rd2n0hg3rxy30ss/gDvVH7gNWsUBZRsYE2ZR1uv1ShL+PnkQv7gV4oParQU6kNNwdFvgTOiT4d3dS/wYbs/uJIYHwTzDvgAZcKHdRabRJNfKnGw5P1dPf2cpK4vFRI0EjWwIDAQAB");
        aliPayConfig.setAppId("2016101700708482");
        aliPayConfig.setAppPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRU4bskYzIzDLGgjC9lRfanTSZj39FL3i6lpH7Is8OjK4YqXga5NvrK9EKpIwfCoWPmOEWw9SpTCt+nQYa50GXQUT7z83GoD96e0xpQyj81PK7dzhlshoZOzuGcv1IQSlGd+3qnUmBpmLoRk2BgD/SshWJWnTUQVrti4redhGPqSRtgEOtHvckRQRGC8K19C1kdVgCe63Rfb85r/IkhPCLTy8+IlFpu8YrV8nv6eOS24zOyP8hubFUT1FB1ojTZb3/yb+AGhARXW+HY4drFv+KaDV831XI2jFNKm9VBkzcdnxTpL2slHCJS45pqKHDKdSFZUUNSFD7DnJoiO7+ofNTAgMBAAECggEACUcj3gaMzmT6+OFm6zORiktvCD65CVm2g3MJbtcSEQO6OajYGh9yStlJRzyqFXE+haRk+mcWNJjATh0IPr902Is6NDlYEc45P0CP6Eh5XWra9EzwNM3iDKpTaon80ZgXYoBVmBzijHxq5BlY6WOer3zfEx3LR6HEhFXcT8eJ53nzw8ISdEKGjr/WZLZoyTBn8/9GjXIlQyycnNCOam2SeWV7+o1T8WzWOqvABfwRqEcGqoZOA5YM6AdJFO9qAhkBo7Ksbd0wCeJHKimU9yrS81nMGB1GAJXeTfzgmp8NyaaZVLfVUg0LfXrREr6W59wivJdDZp9EQR/W3e/8daaqwQKBgQDuLwIFSWG2BMQbA4j/1sMDYTfLRjkWrVtNv05uf6ZIGz2s90Taz8U0/OSgx9dSLQWyJ3Jxr5f9lCDa6hmFgMnbxpHWKduPoVvlViqsCZXhdOlaLCF6/XT8Czp622atFl2XAx8TvXGf0WOr5Lphvk4ydmZ048kKDsQjE6kZJ6VfLwKBgQCcMmNudgjztOTw0CrOpN7cZ6G858gNwpzTxunh7omtZU1i8pgMdbGSMMaymGdFQwpLI9ow0VURXBIBGQ3XqogTX5+sm6bj3ApJTwjw31CHb0UhryDYemTJVc+8NxMeBHuWy0+yI9sC+xGUWeBxjTzhpaJNHB4u6hT06b9h+gnFHQKBgAKfvfP09QCeWNK7XT0ETq2w2n6AzYw0aLcYjnKlMV3F/w3yw21tsIx+5L3otbSQDmoCCT67Yn9GrH386j8QRikHrP+hAmw4qNyD8IBosqADO7bkmZHTTQCh1wkXdbAeePV68VK0WZYbJm/0WyFMiWwA5ewPvl1AAGUqiEhgqW+9AoGBAIDsnmG4j58IuseCKzMxprnp1/gioF+rOOMclkPkLkOvD/cSIip2osD1/avAW80xjreCuj/9KOgRRNMfKOvQWE0VaqiAOXpuTxc/YzPeP4vqbfdlpAjKzZHZgIPsOZ1XOeO3oOp66fVF4ra+kKFpYM0neBWJlehHUlDs9wkZ5yKJAoGBAJKrQBED80fO6AE+VTMeiwnMN1j4hkaDXfB92zBi36Spw9HxzsM1dpcnaRWVc8vgpQ63OM0+AhfSxcJDReJzWiKAw9XOwQD1nlyHRLXLIb5yVXSRXZ6Z3ItL2QVObY4JzB0RJOhKt9TB5zfR1uNXs9urNEkovk6p6yutX+Qf7L0i");
        aliPayConfig.setAppPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkVOG7JGMyMwyxoIwvZUX2p00mY9/RS94upaR+yLPDoyuGKl4GuTb6yvRCqSMHwqFj5jhFsPUqUwrfp0GGudBl0FE+8/NxqA/entMaUMo/NTyu3c4ZbIaGTs7hnL9SEEpRnft6p1JgaZi6EZNgYA/0rIViVp01EFa7YuK3nYRj6kkbYBDrR73JEUERgvCtfQtZHVYAnut0X2/Oa/yJITwi08vPiJRabvGK1fJ7+njktuMzsj/IbmxVE9RQdaI02W9/8m/gBoQEV1vh2OHaxb/img1fN9VyNoxTSpvVQZM3HZ8U6S9rJRwiUuOaaihwynUhWVFDUhQ+w5yaIju/qHzUwIDAQAB");
        aliPayConfig.setCharset("UTF-8");
        aliPayConfig.setGateWay("https://openapi.alipaydev.com/gateway.do");
        aliPayConfig.setNotifyUrl("http://www.baidu.com");
        aliPayConfig.setReturnUrl("http://www.baidu.com");
        aliPayConfig.setSignType("RSA2");
        aliPayConfig.setUid("2088102179998071");

        final AliScanPayUtils aliScanPayUtils = AliScanPayUtils.build(aliPayConfig);
        PrecreateReqParam precreateReqParam = new PrecreateReqParam();
        precreateReqParam.setBody("测试商品的描述");
        precreateReqParam.setGoods_id("1");
        precreateReqParam.setGoods_name("测试商品的名称");
        precreateReqParam.setOut_trade_no("1");
        precreateReqParam.setPrice("0.01");
        precreateReqParam.setQr_code_timeout_express("1m");
        precreateReqParam.setQuantity("2");
        precreateReqParam.setSubject("测试商品订单的标题");
        precreateReqParam.setTotal_amount("0.02");
        aliScanPayUtils.prePay(precreateReqParam);
    }
}

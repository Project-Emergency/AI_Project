package com.example.AI_Project.AI_Project.Service;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "body")
public class XmlResponse {

    private CmmMsgHeader cmmMsgHeader; // 응답 헤더 추가
    private Body body; // 본문

    @Getter
    @Setter
    @XmlRootElement(name = "items")
    public static class CmmMsgHeader {
        private String errMsg; // 에러 메시지
        private String returnAuthMsg; // 인증 메시지
        private String returnReasonCode; // 이유 코드
        private String resultCode; // 결과 코드
        private String resultMsg; // 결과 메시지
    }

    @Getter
    @Setter
    @XmlRootElement(name = "item")
    public static class Body {
        @JacksonXmlElementWrapper(localName = "items")  // <items> 태그를 감싸는 항목
        @JacksonXmlProperty(localName = "item")  // 개별 항목은 <item>으로 표시
        private List<HospitalItem> items; // 병원 정보 리스트
    }
}

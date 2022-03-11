package site.metatcoding.serverproject.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hospital {

    @Id
    private int id; // PK(Primary Key)
    private String addr; // 주소
    private Integer mgtStaDd; // 운영시작일자
    private String pcrPsblYn; // PCR 가능여부
    private String ratPsblYn; // RAT 가능여부
    private Integer recuClCd; // 요양종별코드
    private String sgguCdNm; // 시군구명
    private String sidoCdNm; // 시도명
    private String yadmNm; // 요양기관명
    private String ykihoEnc; // 암호화된 요양기호
    private Double xposWgs84; // 세계지구 x좌표
    private Double yposWgs84; // 세계지구 y좌표
    private Integer xpos; // x좌표
    private Integer ypos; // y좌표
}

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
    private int id;
    private String addr;
    private Integer mgtStaDd;
    private String pcrPsblYn;
    private String ratPsblYn;
    private Integer recuClCd;
    private String sgguCdNm;
    private String sidoCdNm;
    private String yadmNm;
    private String ykihoEnc;
    private Double xposWgs84;
    private Double yposWgs84;
    private Integer xpos;
    private Integer ypos;
}

package site.metatcoding.serverproject.web;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import site.metatcoding.serverproject.domain.Hospital;
import site.metatcoding.serverproject.domain.HospitalRepository;

@RequiredArgsConstructor
@Controller
public class DownloadController {

    private final HospitalRepository hospitalRepository;

    @GetMapping("/")
    public String move() {
        return "hospital/download";
    }

    @GetMapping("/download")
    public String download(Model model) {
        // 1. URL로 다운로드
        RestTemplate rt = new RestTemplate();

        StringBuffer sb = new StringBuffer();

        sb.append("http://3.38.254.72:5000/api/hospital?");
        sb.append("sidoCdNm="); // 시도명
        sb.append("부산");
        sb.append("&sgguCdNm="); // 군구명
        sb.append("부산사하구");

        // Item[] response = rt.getForEntity(sb.toString(), Item[].class);
        Hospital[] response = rt.getForObject(sb.toString(), Hospital[].class);

        List<Hospital> hospitals = Arrays.asList(response);

        // 2. DB에 saveALL() + model에 담기
        hospitalRepository.saveAll(hospitals);
        model.addAttribute("hospitals", hospitals);
        // 3. 리턴
        return "hospital/list";
    }

}

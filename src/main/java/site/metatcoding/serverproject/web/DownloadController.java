package site.metatcoding.serverproject.web;

import java.util.Arrays;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import site.metatcoding.serverproject.domain.Hospital;
import site.metatcoding.serverproject.domain.HospitalRepository;

@RequiredArgsConstructor
@Controller
public class DownloadController {

    // 어노테이션으로 자동으로 생성자를 통한 DI받기 위해 static 사용
    private final HospitalRepository hospitalRepository;

    // 도메인 입력시 다운로드 할 수 있는 페이지로 이동시켜주는 메서드
    @GetMapping("/")
    public String move() {
        return "hospital/download";
    }

    // 데이터를 다운받아 DB에 저장하는 메서드
    @GetMapping("/download")
    public String download() {
        // 다운로드
        RestTemplate rt = new RestTemplate();

        StringBuffer sb = new StringBuffer();

        sb.append("http://3.38.254.72:5000/api/hospital?");
        sb.append("sidoCdNm="); // 시도명
        sb.append("부산");
        sb.append("&sgguCdNm="); // 시군구명
        sb.append("부산사하구");

        // Item[] response = rt.getForEntity(sb.toString(), Item[].class);
        Hospital[] response = rt.getForObject(sb.toString(), Hospital[].class);

        List<Hospital> hospitals = Arrays.asList(response);

        // DB에 saveALL() + model에 담기
        hospitalRepository.saveAll(hospitals);

        return "/";
    }

    // DB로부터 데이터를 불러와 리스트페이지로 전달하는 메서드
    @GetMapping("/list")
    public String list(Model model) {

        // DB로부터 데이터 받기
        List<Hospital> hospitals = hospitalRepository.findAll();

        // model에 담아 리스트페이지로 전달
        model.addAttribute("hospitals", hospitals);

        return "hospital/list";
    }

    // PK(id)로 DB에서 SELECT하여 VIEW에 전달하는 메서드
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {

        // DB로부터 SELECT
        // null값도 담기 위해 Optional 사용
        Optional<Hospital> opHopital = hospitalRepository.findById(id);

        // 데이터가 있다면 model에 담아 view로 전달
        if (opHopital.isPresent()) {
            Hospital hospitalEntity = opHopital.get();
            model.addAttribute("hospital", hospitalEntity);

            return "hospital/detail";
        } else {
            return "hospital/list";
        }
    }

}

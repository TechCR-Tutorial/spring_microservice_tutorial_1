package chamly.learn.spring.microservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versioning")
public class PersonVersioningController {

    @GetMapping(value = "/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Chamly V1");
    }

    @GetMapping(value = "/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Chamly", "V2"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 getParamVersionV1() {
        return new PersonV1("Chamly V1");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 getParamVersionV2() {
        return new PersonV2(new Name("Chamly", "V2"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getHeaderV1() {
        return new PersonV1("Chamly V1");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getHeaderV2() {
        return new PersonV2(new Name("Chamly", "V2"));
    }

    @GetMapping(value = "/person/producer", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getProducerV1() {
        return new PersonV1("Chamly V1");
    }

    @GetMapping(value = "/person/producer", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getProducerV2() {
        return new PersonV2(new Name("Chamly", "V2"));
    }

}

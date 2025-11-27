package pl.put.graph.api.controller;

import pl.put.graph.DegreeCheckResult;
import pl.put.graph.DegreesSequence;
import pl.put.graph.api.dto.*;
import pl.put.graph.domain.DegreeSequenceCheckerPort;
import pl.put.graph.domain.ExactAlgorithmPort;
import pl.put.graph.domain.GreedyAlgorithmPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.graph.domain.RandomGreedyAlgorithmPort;
import pl.put.graph.services.GenerateDegreesService;

@RestController
@RequestMapping("/api")
public class AlgorithmController {

    private final ExactAlgorithmPort exactPort;
    private final GreedyAlgorithmPort greedyPort;
    private final DegreeSequenceCheckerPort prologPort;
    private final RandomGreedyAlgorithmPort randomGreedyPort;
    private final GenerateDegreesService generateService;

    public AlgorithmController(ExactAlgorithmPort exactPort,
                               GreedyAlgorithmPort greedyPort,
                               DegreeSequenceCheckerPort prologPort,
                               RandomGreedyAlgorithmPort randomGreedyPort,
                               GenerateDegreesService generateService) {
        this.exactPort = exactPort;
        this.greedyPort = greedyPort;
        this.prologPort = prologPort;
        this.randomGreedyPort = randomGreedyPort;
        this.generateService = generateService;
    }

    @PostMapping("/exact")
    public AlgorithmResultDto exact(@RequestBody DegreesRequestDto req) {
        DegreesSequence seq = new DegreesSequence(req.getDegrees());
        int value = exactPort.runExact(seq).getValue();
        return new AlgorithmResultDto(value);
    }

    @PostMapping("/greedy")
    public AlgorithmResultDto greedy(@RequestBody DegreesRequestDto req) {
        DegreesSequence seq = new DegreesSequence(req.getDegrees());
        int value = greedyPort.runGreedy(seq).getValue();
        return new AlgorithmResultDto(value);
    }

    @PostMapping("/greedy/random")
    public AlgorithmResultDto greedyRandom(@RequestBody DegreesRequestDto req) {
        DegreesSequence seq = new DegreesSequence(req.getDegrees());
        int value = randomGreedyPort.runRandomGreedy(seq).getValue();
        return new AlgorithmResultDto(value);
    }

    @PostMapping("/degree/check")
    public DegreeCheckResponseDto check(@RequestBody DegreesRequestDto req) {
        DegreesSequence seq = new DegreesSequence(req.getDegrees());
        DegreeCheckResult result = prologPort.check(seq);
        return new DegreeCheckResponseDto(result.getDegrees(), result.isGraphical());
    }

    @PostMapping("/generate")
    public GenerateResponseDto generate(@RequestBody GenerateRequestDto req) {
        var degrees = generateService.generate(req.getN(), req.getP());
        return new GenerateResponseDto(degrees);
    }
}
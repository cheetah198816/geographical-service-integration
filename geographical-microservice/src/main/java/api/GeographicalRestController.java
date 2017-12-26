package api;

import dto.request.RegisterJobRequest;
import dto.response.GetResultsResponse;
import dto.response.RegisterJobResponse;
import dto.response.SearchResultsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.GeographicalService;

/**
 * Created by chetan on 22.12.2017.
 */
@RestController
@RequestMapping("api/")
public class GeographicalRestController {

    @Autowired
    private GeographicalService geographicalService;

    /**
     * Registers a job for processing.
     *
     * @param registerJobRequest object containing file name.
     * @return job Id as a response.
     */
    @RequestMapping(path = "registerJob", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public RegisterJobResponse registerJob(@RequestBody RegisterJobRequest registerJobRequest) {
        return geographicalService.registerJob(registerJobRequest);
    }

    /**
     * Fetches the parsed results for the job Id.
     *
     * @param jobId job Id
     * @return results of the parsed file.
     */
    @RequestMapping(path = "getResults/{jobId}", produces = "application/json", method = RequestMethod.GET)
    public GetResultsResponse getResults(@PathVariable("jobId") Long jobId) {
        return geographicalService.getResults(jobId);
    }

    /**
     * Searches and gets the results according to the code or name or both.
     *
     * @param code  code to search.
     * @param name  name to search.
     * @param jobId jobId to search the results in.
     * @return the results of the search.
     */
    @RequestMapping(path = "searchResults/{jobId}", produces = "application/json", method = RequestMethod.GET)
    public SearchResultsResponse searchResults(@RequestParam(value = "code", required = false) String code, @RequestParam(value = "name", required = false) String name, @PathVariable("jobId") Long jobId) {
        return geographicalService.searchResults(code, name, jobId);
    }

}

package services;

import dto.request.RegisterJobRequest;
import dto.response.GetResultsResponse;
import dto.response.RegisterJobResponse;
import dto.response.SearchResultsResponse;

/**
 * Created by chetan on 22.12.2017.
 */
public interface GeographicalService {
    /**
     * Registers the job for parsing the excel file.
     *
     * @param registerJobRequest dto containing the file name
     * @return dto containing the job Id.
     */
    RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest);

    /**
     * Gets the results of the parsed file.
     *
     * @param jobId jobId.
     * @return dto containing the results.
     */
    GetResultsResponse getResults(Long jobId);

    /**
     * Searches for the results according to the name or code or both.
     *
     * @param code  code
     * @param name  name
     * @param jobId job Id.
     * @return dto containing the results of the search query.
     */
    SearchResultsResponse searchResults(String code, String name, Long jobId);
}

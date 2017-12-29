
import dto.request.RegisterJobRequest;
import dto.response.GetResultsResponse;
import dto.response.RegisterJobResponse;
import dto.response.SearchResultsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import services.GeographicalService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by chetan on 29.12.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GeographicalApplication.class)
public class ExcelIntegrationTest {

    @Autowired
    GeographicalService geographicalService;


    @Test
    public void testRegisterJob() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);

        assertThat(registerJobResponse.getJobId()).isNotZero();
    }

    @Test
    public void testResults() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);
        GetResultsResponse getResultsResponse = geographicalService.getResults(registerJobResponse.getJobId());

        assertThat(getResultsResponse.getSectionDatas().size()).isEqualTo(3);
    }

    @Test
    public void testSearchResultsByCode() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);

        SearchResultsResponse searchResultsResponse = geographicalService.searchResults("GC1", null, registerJobResponse.getJobId());

        assertThat(searchResultsResponse.getSectionDatas().size()).isEqualTo(1);
    }

    @Test
    public void testSearchResultsByName() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);

        SearchResultsResponse searchResultsResponse = geographicalService.searchResults(null, "Geo Class 1", registerJobResponse.getJobId());

        assertThat(searchResultsResponse.getSectionDatas().size()).isEqualTo(1);
    }

    @Test
    public void testSearchResultsByNameAndCode() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);
        SearchResultsResponse searchResultsResponse = geographicalService.searchResults("GC1", "Geo Class 1", registerJobResponse.getJobId());

        assertThat(searchResultsResponse.getSectionDatas().size()).isEqualTo(1);
    }

    @Test
    public void testSearchResultsWhenNameAndCodeNotPassed() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);
        SearchResultsResponse searchResultsResponse = geographicalService.searchResults(null, null, registerJobResponse.getJobId());

        assertThat(searchResultsResponse.getSectionDatas().size()).isEqualTo(0);
    }

    @Test
    public void testSearchResultsWhenWrongCodePassed() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);
        SearchResultsResponse searchResultsResponse = geographicalService.searchResults("GCww", null, registerJobResponse.getJobId());

        assertThat(searchResultsResponse.getSectionDatas().size()).isEqualTo(0);
    }

    @Test
    public void testSearchResultsWhenWrongNamePassed() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);
        SearchResultsResponse searchResultsResponse = geographicalService.searchResults(null, "XY", registerJobResponse.getJobId());

        assertThat(searchResultsResponse.getSectionDatas().size()).isEqualTo(0);
    }

    @Test
    public void testSearchResultsWhenWrongNameAndCodePassed() {
        RegisterJobRequest registerJobRequest = new RegisterJobRequest();
        registerJobRequest.setFileName("geodata.xls");
        RegisterJobResponse registerJobResponse = geographicalService.registerJob(registerJobRequest);
        SearchResultsResponse searchResultsResponse = geographicalService.searchResults("GC2", "XY", registerJobResponse.getJobId());

        assertThat(searchResultsResponse.getSectionDatas().size()).isEqualTo(0);
    }
}

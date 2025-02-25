package com.crowdin.cli.client;

import com.crowdin.client.distributions.model.AddDistributionRequest;
import com.crowdin.client.distributions.model.Distribution;
import com.crowdin.client.distributions.model.DistributionRelease;

import java.util.List;

public class CrowdinClientDistribution extends CrowdinClientCore implements ClientDistribution {

    private final com.crowdin.client.Client client;
    private final String projectId;

    public CrowdinClientDistribution(com.crowdin.client.Client client, String projectId) {
        this.client = client;
        this.projectId = projectId;
    }

    @Override
    public List<Distribution> listDistribution() {
        return executeRequestFullList((limit, offset) -> this.client.getDistributionsApi()
            .listDistributions(Long.valueOf(projectId), limit, offset));
    }

    @Override
    public Distribution addDistribution(AddDistributionRequest distributionRequest) {
        return executeRequest(() -> this.client.getDistributionsApi()
            .addDistribution(Long.valueOf(projectId), distributionRequest)
            .getData());
    }

    @Override
    public DistributionRelease release(String hash) {
        return executeRequest(() -> this.client.getDistributionsApi()
                                               .createDistributionRelease(Long.valueOf(projectId), hash)
                                               .getData());
    }

    @Override
    public DistributionRelease getDistributionRelease(String hash) {
        return executeRequest(() -> this.client.getDistributionsApi()
                                               .getDistributionRelease(Long.valueOf(projectId), hash)
                                               .getData());
    }

}

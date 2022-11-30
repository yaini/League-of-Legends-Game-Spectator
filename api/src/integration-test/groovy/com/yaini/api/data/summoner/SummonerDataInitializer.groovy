package com.yaini.api.data.summoner


import com.yaini.data.entity.SummonerEntity
import com.yaini.data.repository.SummonerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SummonerDataInitializer {
    static final int DATA_SIZE = 10

    @Autowired
    private SummonerRepository repository

    protected Collection<SummonerEntity> data

    void execute() {
        repository.deleteAll()

        this.setData()
    }

    private void setData() {
        Collection<SummonerEntity> data = new ArrayList<>()

        for (int i = 0; i < DATA_SIZE; i++) {

            data.add(repository.save(FakeSummonerEntity.create()))
        }

        this.data = data
    }

}

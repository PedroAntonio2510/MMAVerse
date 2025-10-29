package br.com.mmaverse.service;

import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.entity.Ranking;
import br.com.mmaverse.exception.ResourceNotFoundException;
import br.com.mmaverse.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;
    private final OrganizationService organizationService;
    @Autowired
    private ContenderService contenderService;
    
    public RankingService(RankingRepository rankingRepository, OrganizationService organizationService) {
        this.rankingRepository = rankingRepository;
        this.organizationService = organizationService;
    }

    public List<Ranking> findAll() {
        return rankingRepository.findAll();
    }

    public Ranking findById(Long id) {
        return rankingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ranking not found with id: " + id));
    }

    public Ranking save(Ranking ranking) {
        Organization organization = organizationService.findById(ranking.getOrganization().getId());
        Set<Contender> contenders = ranking.getContenders().stream().map(contender -> contenderService.findById(contender.getId())).collect(Collectors.toSet());
        ranking.setOrganization(organization);
        ranking.setContenders(contenders);
        return rankingRepository.save(ranking);
    }

    public void delete(Long id) {
        /*
        *  Localizo o ranking, e coloco os ranking_id dos contenders null e
        * atualizo para não dar erro no banco de dados ao tentar deletar um ranking
        * */
        Ranking ranking = findById(id);
        ranking.getContenders().forEach(contender -> {
            contender.setRanking(null);
            contenderService.update(contender.getId(), contender);
        });
        rankingRepository.delete(ranking);
    }

    public Ranking update(Long id, Ranking ranking) {
        Ranking rankingToUpdate = findById(id);
        Organization organization = organizationService.findById(ranking.getOrganization().getId());

        rankingToUpdate.setName(ranking.getName());
        rankingToUpdate.setCategory(ranking.getCategory());
        rankingToUpdate.setOrganization(organization);
        rankingToUpdate.setContenders(ranking.getContenders());

        return rankingRepository.save(rankingToUpdate);
    }

    public Ranking addContender(Long rankingId, Long contenderId) {
        Ranking ranking = findById(rankingId);
        Contender contender = contenderService.findById(contenderId);
        contender.setRanking(ranking);
        return rankingRepository.save(ranking);
    }

    public Ranking removeContender(Long rankingId, Long contenderId) {
        Ranking ranking = findById(rankingId);
        Contender contender = contenderService.findById(contenderId);
        contender.setRanking(null);
        ranking.getContenders().remove(contender);
        return rankingRepository.save(ranking);
    }
}

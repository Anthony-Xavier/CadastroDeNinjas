package dev.java10x.CadastroDeNinjas.services;

import dev.java10x.CadastroDeNinjas.model.MissaoModel;
import dev.java10x.CadastroDeNinjas.model.NinjaModel;
import dev.java10x.CadastroDeNinjas.repositories.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<MissaoModel> listarMissoes() {
        return missaoRepository.findAll();
    }

    public MissaoModel buscarPorId(Long id) {
        return missaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada!"));
    }

    public MissaoModel insert(MissaoModel missao) {
        return missaoRepository.save(missao);
    }

    public void delete(Long id) {
        missaoRepository.deleteById(id);
    }

    public MissaoModel update(Long id, MissaoModel missao) {
        MissaoModel entity = missaoRepository.getReferenceById(id);
        updateData(entity, missao);
        return missaoRepository.save(entity);
    }

    private void updateData(MissaoModel entity, MissaoModel missao) {
        entity.setNomeMissao(missao.getNomeMissao());
        entity.setDificuldade(missao.getDificuldade());
    }

    public MissaoModel adicionarNinjaNaMissao(Long missaoId, NinjaModel ninja) {
        MissaoModel missao = missaoRepository.findById(missaoId)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));

        ninja.setMissoes(missao);
        missao.getNinjas().add(ninja);

        return missaoRepository.save(missao);
    }

}
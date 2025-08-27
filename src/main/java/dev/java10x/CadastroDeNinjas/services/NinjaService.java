package dev.java10x.CadastroDeNinjas.services;

import dev.java10x.CadastroDeNinjas.model.MissaoModel;
import dev.java10x.CadastroDeNinjas.model.NinjaModel;
import dev.java10x.CadastroDeNinjas.repositories.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public List<NinjaModel> listarNinjas(){
        return ninjaRepository.findAll();
    }

    public NinjaModel insert(NinjaModel ninja){
        return ninjaRepository.save(ninja);
    }

    public void delete(Long id){
        ninjaRepository.deleteById(id);
    }

    public NinjaModel update(Long id, NinjaModel ninja){
        NinjaModel entity = ninjaRepository.getReferenceById(id);
        updateData(entity,ninja);
        return ninjaRepository.save(entity);
    }

    private void updateData(NinjaModel entity, NinjaModel ninja) {
        entity.setName(ninja.getName());
        entity.setEmail(ninja.getEmail());
        entity.setAge(ninja.getAge());
        entity.setMissoes(ninja.getMissoes());
    }

    public NinjaModel atribuirMissao(Long ninjaId, MissaoModel missao) {
        NinjaModel ninja = ninjaRepository.findById(ninjaId)
                .orElseThrow(() -> new RuntimeException("Ninja não encontrado"));

        // Associa a missão ao ninja
        ninja.setMissoes(missao);

        return ninjaRepository.save(ninja);
    }

}

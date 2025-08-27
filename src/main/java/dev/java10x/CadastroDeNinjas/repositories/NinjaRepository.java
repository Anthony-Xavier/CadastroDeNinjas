package dev.java10x.CadastroDeNinjas.repositories;

import dev.java10x.CadastroDeNinjas.model.NinjaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepository extends JpaRepository<NinjaModel,Long> {
}

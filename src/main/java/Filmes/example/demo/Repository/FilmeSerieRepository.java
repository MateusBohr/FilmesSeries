package Filmes.example.demo.Repository;

import Filmes.example.demo.Model.FilmeSerie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeSerieRepository extends JpaRepository<FilmeSerie, Long> {
}

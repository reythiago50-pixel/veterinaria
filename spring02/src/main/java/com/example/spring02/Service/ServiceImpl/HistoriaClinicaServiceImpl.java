package com.example.spring02.Service.ServiceImpl;

import com.example.spring02.entity.HistoriaClinica;
import com.example.spring02.entity.Mascota;
import com.example.spring02.Repository.HistoriaClinicaRepository;
import com.example.spring02.Repository.MascotaRepository;
import com.example.spring02.Service.HistoriaClinicaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    public List<HistoriaClinica> listarTodas() {
        return historiaRepository.findAll();
    }

    @Override
    public HistoriaClinica buscarPorId(Long id) {
        return historiaRepository.findById(id).orElseThrow(() -> new RuntimeException("Historia Clínica no encontrada"));
    }

    @Override
    public HistoriaClinica crear(HistoriaClinica historia, Long mascotaId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        historia.setMascota(mascota);
        return historiaRepository.save(historia);
    }

    @Override
    public HistoriaClinica actualizar(Long id, HistoriaClinica historia) {
        HistoriaClinica existente = buscarPorId(id);
        existente.setFechaApertura(historia.getFechaApertura());
        existente.setAntecedentes(historia.getAntecedentes());
        existente.setObservaciones(historia.getObservaciones());
        return historiaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!historiaRepository.existsById(id)) {
            throw new RuntimeException("Historia Clínica no existe");
        }
        historiaRepository.deleteById(id);
    }
}
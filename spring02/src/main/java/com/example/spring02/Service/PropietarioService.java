package com.example.spring02.Service;
import com.example.spring02.entity.Propietario;
import java.util.List;

public interface PropietarioService {
    List<Propietario> listarTodos();
    Propietario buscarPorId(Long id);
    Propietario guardar(Propietario propietario);
    Propietario actualizar(Long id, Propietario propietario);
    void eliminar(Long id);
}
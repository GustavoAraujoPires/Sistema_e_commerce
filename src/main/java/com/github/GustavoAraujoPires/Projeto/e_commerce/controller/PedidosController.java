package com.github.GustavoAraujoPires.Projeto.e_commerce.controller;

import com.github.GustavoAraujoPires.Projeto.e_commerce.dto.PedidoRequestDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.dto.PedidoResponseDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Pedido;
import com.github.GustavoAraujoPires.Projeto.e_commerce.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidosController {
    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> salvarPedidos(@RequestBody PedidoRequestDTO dto){
        service.salvarPedido(dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}/pagar")
    public ResponseEntity<Pedido> pagarPedido(@PathVariable Long id){
        var pagar = service.pagarPedido(id);
        return ResponseEntity.ok().body(pagar);
    }

    @PatchMapping("{id}/entregar")
    public ResponseEntity<Pedido> entregarPedido(@PathVariable Long id){
        var entregar = service.entregarPedido(id);
        return ResponseEntity.ok().body(entregar);
    }

    @PatchMapping("{id}/cancelar")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id){
       service.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }
}

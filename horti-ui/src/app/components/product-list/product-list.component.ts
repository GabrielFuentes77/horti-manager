import { Component, OnInit } from '@angular/core'; // Adicionamos o OnInit
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service'; // Importe o seu Service

@Component({
  selector: 'app-product-list', // Simplifiquei o seletor (padrão Angular)
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css',
})
export class ProductList implements OnInit {
  
  // 1. Declaramos a variável que o HTML está procurando
  produtos: any[] = [];

  // 2. Injetamos o Service para buscar os dados do Java
  constructor(private productService: ProductService) {}

  // 3. O ngOnInit roda assim que a tela abre
  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.productService.listarTodos().subscribe({
      next: (dados) => {
        this.produtos = dados;
        console.log('Dados recebidos do Java:', dados);
      },
      error: (err) => {
        console.error('Erro ao conectar com o Back-end:', err);
      }
    });
  }
}
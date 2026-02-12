import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// 1. Importe o componente que você criou:
import { ProductList } from './components/product-list/product-list.component'; 

@Component({
  selector: 'app-root',
  standalone: true,
  // 2. Adicione ele aqui na lista de imports:
  imports: [RouterOutlet, ProductList], 
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'horti-ui';
}
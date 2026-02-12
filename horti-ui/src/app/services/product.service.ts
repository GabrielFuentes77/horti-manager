import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Importamos o motor de busca
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  // URL da sua API Java 
  private apiUrl = 'http://localhost:8080/api/produtos'; 

  constructor(private http: HttpClient) { }

  // Método para buscar a lista de plantas no PostgreSQL
  listarTodos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
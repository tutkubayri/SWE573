import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostTypeService {

  private baseUrl = 'http://localhost:8080/communities';
  constructor(private http: HttpClient) { }

  getPostTypes(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/id/${id}/postTypes`);
   }
}
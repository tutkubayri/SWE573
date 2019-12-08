import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormAreaService {

  private baseUrl = 'http://localhost:8080/formAreas';

  constructor(private http: HttpClient) { }

  createFormArea(formArea: Object, id:number): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add/${id}`, formArea);
  }

  getFormAreasByPostTypeId(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/${id}`);
   }
}

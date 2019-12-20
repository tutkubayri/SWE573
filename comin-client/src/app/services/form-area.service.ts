import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FormAreaService {

  private baseUrl = 'http://localhost:8080/formAreas';
  private enumBaseUrl = 'http://localhost:8080/enums';

  constructor(private http: HttpClient) { }

  createFormArea(formArea: Object, id:number): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add/${id}`, formArea);
  }

  createEnum(enumOfForm: Object, id: number): Observable<Object>{
    return this.http.post(`${this.baseUrl}/enum/${id}`, enumOfForm);
  }

  getEnumByFormAreaId(id: number): Observable<any>{
    return this.http.get(`${this.enumBaseUrl}/${id}`);
   }
}

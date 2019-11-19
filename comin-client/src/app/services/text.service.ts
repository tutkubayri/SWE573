/* import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TextService {

  constructor(private http: HttpClient) { }

  createText(text: Text): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/add`, text);
  }
} */

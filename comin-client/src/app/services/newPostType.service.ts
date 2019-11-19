import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewPostTypeService {

  private baseUrl = 'http://localhost:8080/postTypes';

  constructor(private http: HttpClient) { }

  createPostType(postType: Object, id:number): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add/${id}`, postType);
  }
}

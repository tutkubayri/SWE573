import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private baseUrl = 'http://localhost:8080/posts';
  constructor(private http: HttpClient) { }

  createPost(id: number, post: Object): Observable<any>{
    return this.http.post(`${this.baseUrl}/add/${id}`, post);
   }
}
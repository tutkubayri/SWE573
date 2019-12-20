import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewPostTypeService {

  private baseUrl = 'http://localhost:8080/postTypes';
  private wikiUrl = 'https://www.wikidata.org/w/api.php?action=wbsearchentities&limit=100&language=en&format=json&search=';

  constructor(private http: HttpClient) { }

  createPostType(postType: Object, id:number): Observable<Object> {
    return this.http.post(`${this.baseUrl}/add/${id}`, postType);
  }

  tagSearch(tag:string): Observable<any> {
    return this.http.get(`${this.wikiUrl}${tag}` + `&origin=*`);
  }
}

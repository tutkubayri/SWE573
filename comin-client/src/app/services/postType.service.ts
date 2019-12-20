import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostTypeService {

  private baseUrl = 'http://localhost:8080/postTypes';
  private wikiUrl = 'https://www.wikidata.org/w/api.php?action=wbsearchentities&limit=100&language=en&format=json&search=';

  constructor(private http: HttpClient) { }

  getPostTypeById(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/${id}`);
   }

   getPostTypeByName(name: string): Observable<any>{
    return this.http.get(`${this.baseUrl}/${name}`);
   }

   getPostTypesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  tagSearch(tag:string): Observable<any> {
    return this.http.get(`${this.wikiUrl}${tag}` + `&origin=*`);
  }
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {

  private baseUrl = 'http://localhost:8080/communities';
  private wikiUrl = 'https://www.wikidata.org/w/api.php?action=wbsearchentities&limit=50&language=en&format=json&search=';
  constructor(private http: HttpClient) { }

  getCommunityById(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}/id/${id}`);
   }
   
  createCommunity(community: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/add`, community);
  }
  updateCommunity(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
  deleteCommunity(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }
  getCommunitiesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  getCommunitiesByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/age/${name}`);
  }
  getCommunitiesByTag(tag: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/age/${tag}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
  }
  tagSearch(tag:string): Observable<any> {
    return this.http.get(`${this.wikiUrl}${tag}` + `&origin=*`);
  }
}
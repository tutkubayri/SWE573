import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {

  private baseUrl = 'http://localhost:8080/api/communities';
  constructor(private http: HttpClient) { }
  getCommunity(id: number){
    return this.http.get(`${this.baseUrl + 'communities'}/${id}`); 
   }

  /* getCommunity(id: number): Observable<Object> {

    let newPath = this.baseUrl
    if (id) {
      return this.http.get(`${this.baseUrl}/${id}`);
    };
  } */
  createCommunity(community: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/create`, community);
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
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../user';

@Injectable()
export class UserService {
    private baseUrl = 'http://localhost:8080/user';
    constructor(private http: HttpClient) { }

    getCurrentUser() {
      return this.http.get(`${this.baseUrl}/me`);
    }

    getByUsername(username: String) {
        return this.http.get(`/users/` + username);
    }

    register(user: User) {
        return this.http.post(`/auth/signup`, user);
    }

    update(user: User) {
        return this.http.put(`/users/` + user.id, user);
    }

    delete(id: number) {
        return this.http.delete(`/users/` + id);
    }
}
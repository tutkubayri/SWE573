import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions,Response} from '@angular/http';
import { map } from "rxjs/operators";
import { AppComponent } from './app.component';

@Injectable()
export class AuthService {
  constructor(public http: Http) { }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  public logIn(user: User){

    let headers = new Headers();
    headers.append('Accept', 'application/json')
    // creating base64 encoded String from user name and password
    var base64Credential: string = btoa( user.username+ ':' + user.password);
    headers.append("Authorization", "Basic " + base64Credential);

    let options = new RequestOptions();
    options.headers=headers;

    return this.http.get(AppComponent.API_URL+"/account/login" , options)
      .pipe(map((response: Response) => {
      let user = response.json().principal;
      if (user) {
        sessionStorage.setItem('username', JSON.stringify(user));
      }
    }));
  }

  logOut() {
    // remove user from local storage to log user out
    return this.http.post(AppComponent.API_URL+"logout",{})
      .pipe(map((response: Response) => {
        sessionStorage.removeItem('username');
      }));
  }
}
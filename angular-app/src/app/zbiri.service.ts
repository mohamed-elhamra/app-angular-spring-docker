import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZbiriService {

  constructor(private http: HttpClient) {  }

  get(){
    return this.http.get<any[]>(`${environment.apiURL}/users/test`);
  }

}

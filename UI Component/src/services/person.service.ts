import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Person } from 'src/model/person';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from 'src/model/user';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
 
  person: Person[];
  personSubject: BehaviorSubject<Array<Person>>;
  user: User;
  userName : string;

  constructor(private http: HttpClient) {

    this.person = [];
    this.personSubject = new BehaviorSubject([]);
    this.authenticateUser();

  }

  setBearerToken(token) {
    localStorage.setItem('Token', token);
  }

  getBearerToken() {
  
    let token = localStorage.getItem('Token');

    this.http.post(`http://localhost:8080/api/v1/auth/validate`, token,{responseType: 'text'})
    .subscribe(res => {
        if(res =='validToken'){
          return localStorage.getItem('Token');      
        }else{
          this.authenticateUser();
        }
      });

    return localStorage.getItem('Token');
  }

  authenticateUser() {
    this.user = new User();
    
    let response =  this.http.post(`http://localhost:8080/api/v1/auth/login`, this.user,{responseType: 'text'})
    .subscribe(res => {
        this.setBearerToken(res);
        this.fetchPersonDetailsFromServer();
      });
  }


  fetchPersonDetailsFromServer() {

    this.http.get<Person[]>(`http://localhost:8080/api/v1/person/list`,{
      headers: new HttpHeaders({
        'authorization': `${this.getBearerToken()}`
      })
    })
      .subscribe(personResponse => {
        this.person = personResponse;
        this.personSubject.next(this.person);
      });
  }

  getPerson(): BehaviorSubject<Person[]> {
    return this.personSubject;
  }

  savePerson(updatePerson : Person){
    return this.http.post(`http://localhost:8080/api/v1/person/person`, updatePerson,{
      headers: new HttpHeaders({
        'Authorization': `${this.getBearerToken()}`
      }),
      responseType: "text"
    })
    .pipe(
      tap(editedPerson => {
        const foundPerson = this.person.find(person => person.id === updatePerson.id);
        Object.assign(foundPerson, editedPerson);
        this.personSubject.next(this.person);
      })
    );
  }

  addPerson(addPerson : Person){
    return this.http.post(`http://localhost:8080/api/v1/person/person`, addPerson, {
      headers: new HttpHeaders({
        'Authorization': `${this.getBearerToken()}`
      }),
      responseType: "text"
    })
    .pipe(
      tap(addedPerson => {
        this.fetchPersonDetailsFromServer();
      })
    );
  }

  deletePerson(personId : Number){

    return this.http.delete(`http://localhost:8080/api/v1/person/deletePerson/${personId}`,{
      headers: new HttpHeaders({
        'Authorization': `${this.getBearerToken()}`
      }),
      responseType: "text"
    }).pipe(
      tap(deletedPerson => {
        this.fetchPersonDetailsFromServer();
      })
    );
  }

}
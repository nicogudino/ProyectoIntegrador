import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Skill } from '../model/skill';

@Injectable({
  providedIn: 'root'
})
export class SkillService {
  expURL = 'http://localhost:8080/skill/';

  constructor(private httClient: HttpClient) { }

  public lista(): Observable<Skill[]>{
    return this.httClient.get<Skill[]>(this.expURL + 'lista');
  }
  public detail(id:number): Observable<Skill> {
    return this.httClient.get<Skill>(this.expURL+ `detail/${id}`);
  }
  public save(skill: Skill): Observable<any>{
    return this.httClient.post<any>(this.expURL + 'create', skill);
  }
  public update(id:number, skill: Skill): Observable<any>{
    return this.httClient.put<any>(this.expURL + `update/${id}`, skill);
  }
  public delete(id: number): Observable<any>{
    return this.httClient.delete(this.expURL +`delete/${id}`);
  }
}

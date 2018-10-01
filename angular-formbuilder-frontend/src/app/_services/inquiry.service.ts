import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
// import { catchError } from 'rxjs/operators';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { InquiryDTO } from '../_model/inquiry-dto';
import { environment } from '../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable()
export class InquiryService {
    private baseUrl = environment.apiUrl + '/inqr/';  // URL to web api

    constructor(private http: HttpClient) { }

    getTableDetailById(idForm: string): Observable<InquiryDTO[]> {
        return this.http.get<InquiryDTO[]>(this.baseUrl + idForm);

        // .pipe(catchError(this.handleError));
    }

    searchTableContainer(idForm: string, containers: string): Observable<InquiryDTO[]> {
        return this.http.post<InquiryDTO[]>(
            this.baseUrl + idForm, containers, httpOptions
        );
        // .pipe(catchError(this.handleError)
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            console.error('An error occurred:', error.error.message);
        } else {
            console.error(
                `Backend returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
        return new ErrorObservable(
            'Something bad happened; please try again later.');
    }
}

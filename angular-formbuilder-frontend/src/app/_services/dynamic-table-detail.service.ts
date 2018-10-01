import { TableContentModel } from './../_model/table/table-content';
import { TableModel } from './../_model/table/table-model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
// import { catchError } from 'rxjs/operators';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { environment } from '../../environments/environment';
import { KeyValueModel } from '../_model/key-value';
// import { TableWrapper } from '../wrapper/table.wrapper';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable()
export class DynamicTableDetailService {

    private baseUrl = environment.apiUrl + '/table/';  // URL to web api

    constructor(private http: HttpClient) { }

    getFormTableById(idForm: string): Observable<TableModel> {
        return this.http.get<TableModel>(this.baseUrl + 'formtable/' + idForm + '/');
        //.pipe(catchError(this.handleError));
    }

    getSchemaTableById(idForm: string): Observable<TableContentModel[]> {
        return this.http.get<TableContentModel[]>(this.baseUrl + 'schematable/' + idForm + '/');
        //.pipe(catchError(this.handleError));
    }

    getLovByQuery(idLov: string, params: KeyValueModel[]): Observable<TableContentModel[]> {
        // console.log(idLov + ' getlovByQuery ' + JSON.stringify(params));
        return this.http.post<TableContentModel[]>(
            this.baseUrl + 'lov/' + idLov + '/', params, httpOptions
        );
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

<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;
use App\Models\User;
use App\Models\Labor;
use App\Models\Pupuk;
use App\Models\Benih;
use App\Models\Alat;
use App\Models\Kelurahan;
use App\Models\Kecamatan;
use App\Models\Kabupaten;
use App\Models\Provinsi;

class ApiController extends Controller
{
    public function login(Request $request)
    {
        $login = Auth::Attempt($request->all());
        if ($login) {
            $user = Auth::user();
            // $user->api_token = Str::random(100);
            // $user->save();
            // $user->makeVisible('api_token');

            return response()->json([
                'response_code' => 200,
                'message' => 'Login Berhasil',
                'conntent' => $user
            ]);
        }else{
            return response()->json([
                'response_code' => 404,
                'message' => 'Username atau Password Tidak Ditemukan!'
            ]);
        }
    }
    //pupuk
    public function getpupuk()
    {
        $pupuk = Pupuk::select('*')
            ->get();

     if ($pupuk) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $pupuk
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        } 
    }  

    public function entrypupuk(Request $request)

    {

        $pupuk = Pupuk::create([
            'pupuk' => $request->pupuk,
            'jumlah' => $request->jumlah,
        ]);




        if ($pupuk) { // jika simpan santri berhasil

            return response()->json([
                'response_code' => 200,
                'message' => 'Simpan Data Berhasil',
            ]);

        }else{ // jika simpan santri gagal

            return response()->json([
                'response_code' => 404,
                'message' => 'Simpan Data Gagal'
            ]);

        }

    }

    public function hapuspupuk(Request $request)
    {
        $pupuk = Pupuk::where('id', $request->id)
                  ->delete();

        if ($pupuk) {
            return response()->json([
                'response_code' => 200,
                'message' => 'Hapus Data Berhasil'
            ]);
        }else{
            return response()->json([
                'response_code' => 404,
                'message' => 'Hapus Data Gagal'
            ]);
        }
    }

    function editpupuk(Request $request) {
        $pupuk = Pupuk::where('id', $request->id)
                  ->update([
                        'pupuk' => $request->pupuk,
                        'jumlah' => $request->jumlah,
                  ]);

        if ($pupuk) {
            return response()->json([
                'response_code' => 200,
                'message' => 'ok',
            ]);
        } else {
            return response()->json([
                'response_code' => 500,
                'message' => 'internal error',
            ]);
        }
    }

    //benih

    public function getbenih()
    {
        $benih = Benih::select('*')
            ->get();

     if ($benih) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $benih
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        } 
    }  

    public function entrybenih(Request $request)

    {

        $benih = Benih::create([
            'benih' => $request->benih,
            'jumlah' => $request->jumlah,
        ]);




        if ($benih) { // jika simpan santri berhasil

            return response()->json([
                'response_code' => 200,
                'message' => 'Simpan Data Berhasil',
            ]);

        }else{ // jika simpan santri gagal

            return response()->json([
                'response_code' => 404,
                'message' => 'Simpan Data Gagal'
            ]);

        }

    }

    public function hapusbenih(Request $request)
    {
        $benih = Benih::where('id', $request->id)
                  ->delete();

        if ($benih) {
            return response()->json([
                'response_code' => 200,
                'message' => 'Hapus Data Berhasil'
            ]);
        }else{
            return response()->json([
                'response_code' => 404,
                'message' => 'Hapus Data Gagal'
            ]);
        }
    }

    function editbenih(Request $request) {
        $benih = Benih::where('id', $request->id)
                  ->update([
                        'benih' => $request->benih,
                        'jumlah' => $request->jumlah,
                  ]);

        if ($benih) {
            return response()->json([
                'response_code' => 200,
                'message' => 'ok',
            ]);
        } else {
            return response()->json([
                'response_code' => 500,
                'message' => 'internal error',
            ]);
        }
    }
    
    //alat

    public function getalat()
    {
        $alat = alat::select('*')
            ->get();

     if ($alat) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $alat
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        } 
    }  

    public function entryalat(Request $request)

    {

        $alat = Alat::create([
            'alat' => $request->alat,
            'jumlah' => $request->jumlah,
        ]);




        if ($alat) { // jika simpan santri berhasil

            return response()->json([
                'response_code' => 200,
                'message' => 'Simpan Data Berhasil',
            ]);

        }else{ // jika simpan santri gagal

            return response()->json([
                'response_code' => 404,
                'message' => 'Simpan Data Gagal'
            ]);

        }

    }

    public function hapusalat(Request $request)
    {
        $alat = Alat::where('id', $request->id)
                  ->delete();

        if ($alat) {
            return response()->json([
                'response_code' => 200,
                'message' => 'Hapus Data Berhasil'
            ]);
        }else{
            return response()->json([
                'response_code' => 404,
                'message' => 'Hapus Data Gagal'
            ]);
        }
    }

    function editalat(Request $request) {
        $alat = Alat::where('id', $request->id)
                  ->update([
                        'alat' => $request->alat,
                        'jumlah' => $request->jumlah,
                  ]);

        if ($alat) {
            return response()->json([
                'response_code' => 200,
                'message' => 'ok',
            ]);
        } else {
            return response()->json([
                'response_code' => 500,
                'message' => 'internal error',
            ]);
        }
    }

//helper
    public function kelurahan(Request $request)
    {
        $kelurahan = Kelurahan::select('*')
            ->where('id_kec', $request->id_kec)
            ->where('in_active', 0)
            ->get();

            if ($kelurahan) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $kelurahan
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }   
    public function kecamatan(Request $request)
    {
        $kecamatan = Kecamatan::select('*')
            ->where('id_kab', '=', $request->id_kab)
            ->get();

            if ($kecamatan) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $kecamatan
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }
    public function kabupaten(Request $request)
    {
        $kabupaten = Kabupaten::select('*')
            ->where('id_prov', '=', $request->id_prov)
            ->get();

            if ($kabupaten) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $kabupaten
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }    




      
    public function provinsi(Request $request)
    {
        $provinsi = Provinsi::select('*')
            ->get();

            if ($provinsi) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $provinsi
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }   
}

PGDMP                      |            Vet_Cey    15.6    16.2 G    @           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            A           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            B           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            C           1262    60988    Vet_Cey    DATABASE     ~   CREATE DATABASE "Vet_Cey" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE "Vet_Cey";
                postgres    false            �            1259    60991    animals    TABLE     �  CREATE TABLE public.animals (
    animal_id integer NOT NULL,
    animal_breed character varying(255) NOT NULL,
    animal_color character varying(255) NOT NULL,
    animal_birthday date NOT NULL,
    animal_gender character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255) NOT NULL,
    animal_customer_id integer NOT NULL
);
    DROP TABLE public.animals;
       public         heap    postgres    false            �            1259    60990    animals_animal_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.animals_animal_customer_id_seq;
       public          postgres    false    216            D           0    0    animals_animal_customer_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.animals_animal_customer_id_seq OWNED BY public.animals.animal_customer_id;
          public          postgres    false    215            �            1259    60989    animals_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.animals_animal_id_seq;
       public          postgres    false    216            E           0    0    animals_animal_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.animals_animal_id_seq OWNED BY public.animals.animal_id;
          public          postgres    false    214            �            1259    61003    appointment    TABLE     �   CREATE TABLE public.appointment (
    appointment_id integer NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    appointment_animal_id integer NOT NULL,
    appointment_doctor_id integer NOT NULL
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    61001 %   appointment_appointment_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointment_appointment_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.appointment_appointment_animal_id_seq;
       public          postgres    false    220            F           0    0 %   appointment_appointment_animal_id_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.appointment_appointment_animal_id_seq OWNED BY public.appointment.appointment_animal_id;
          public          postgres    false    218            �            1259    61002 %   appointment_appointment_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointment_appointment_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.appointment_appointment_doctor_id_seq;
       public          postgres    false    220            G           0    0 %   appointment_appointment_doctor_id_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.appointment_appointment_doctor_id_seq OWNED BY public.appointment.appointment_doctor_id;
          public          postgres    false    219            �            1259    61000    appointment_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointment_appointment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.appointment_appointment_id_seq;
       public          postgres    false    220            H           0    0    appointment_appointment_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.appointment_appointment_id_seq OWNED BY public.appointment.appointment_id;
          public          postgres    false    217            �            1259    61013    available_date    TABLE     �   CREATE TABLE public.available_date (
    available_date_id integer NOT NULL,
    available_date_date date NOT NULL,
    available_date_doctor_id integer NOT NULL
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    61012 +   available_date_available_date_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_date_available_date_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 B   DROP SEQUENCE public.available_date_available_date_doctor_id_seq;
       public          postgres    false    223            I           0    0 +   available_date_available_date_doctor_id_seq    SEQUENCE OWNED BY     {   ALTER SEQUENCE public.available_date_available_date_doctor_id_seq OWNED BY public.available_date.available_date_doctor_id;
          public          postgres    false    222            �            1259    61011 $   available_date_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_date_available_date_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.available_date_available_date_id_seq;
       public          postgres    false    223            J           0    0 $   available_date_available_date_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.available_date_available_date_id_seq OWNED BY public.available_date.available_date_id;
          public          postgres    false    221            �            1259    61021 	   customers    TABLE     G  CREATE TABLE public.customers (
    customer_id integer NOT NULL,
    customer_address character varying(255) NOT NULL,
    customer_city character varying(255) NOT NULL,
    customer_mail character varying(255) NOT NULL,
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255) NOT NULL
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    61020    customers_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.customers_customer_id_seq;
       public          postgres    false    225            K           0    0    customers_customer_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;
          public          postgres    false    224            �            1259    61030    doctors    TABLE       CREATE TABLE public.doctors (
    doctor_id integer NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255),
    doctor_name character varying(255),
    doctor_phone character varying(255)
);
    DROP TABLE public.doctors;
       public         heap    postgres    false            �            1259    61029    doctors_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doctors_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.doctors_doctor_id_seq;
       public          postgres    false    227            L           0    0    doctors_doctor_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.doctors_doctor_id_seq OWNED BY public.doctors.doctor_id;
          public          postgres    false    226            �            1259    61040    vaccine    TABLE       CREATE TABLE public.vaccine (
    vaccine_id integer NOT NULL,
    vaccine_code character varying(255),
    vaccine_name character varying(255),
    vaccine_protection_finish_date date,
    vaccine_protection_start_date date,
    vaccine_animal_id integer NOT NULL
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    61039    vaccine_vaccine_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccine_vaccine_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.vaccine_vaccine_animal_id_seq;
       public          postgres    false    230            M           0    0    vaccine_vaccine_animal_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.vaccine_vaccine_animal_id_seq OWNED BY public.vaccine.vaccine_animal_id;
          public          postgres    false    229            �            1259    61038    vaccine_vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccine_vaccine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.vaccine_vaccine_id_seq;
       public          postgres    false    230            N           0    0    vaccine_vaccine_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.vaccine_vaccine_id_seq OWNED BY public.vaccine.vaccine_id;
          public          postgres    false    228            �           2604    60994    animals animal_id    DEFAULT     v   ALTER TABLE ONLY public.animals ALTER COLUMN animal_id SET DEFAULT nextval('public.animals_animal_id_seq'::regclass);
 @   ALTER TABLE public.animals ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    216    214    216            �           2604    60995    animals animal_customer_id    DEFAULT     �   ALTER TABLE ONLY public.animals ALTER COLUMN animal_customer_id SET DEFAULT nextval('public.animals_animal_customer_id_seq'::regclass);
 I   ALTER TABLE public.animals ALTER COLUMN animal_customer_id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    61006    appointment appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointment ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointment_appointment_id_seq'::regclass);
 I   ALTER TABLE public.appointment ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    220    217    220            �           2604    61007 !   appointment appointment_animal_id    DEFAULT     �   ALTER TABLE ONLY public.appointment ALTER COLUMN appointment_animal_id SET DEFAULT nextval('public.appointment_appointment_animal_id_seq'::regclass);
 P   ALTER TABLE public.appointment ALTER COLUMN appointment_animal_id DROP DEFAULT;
       public          postgres    false    220    218    220            �           2604    61008 !   appointment appointment_doctor_id    DEFAULT     �   ALTER TABLE ONLY public.appointment ALTER COLUMN appointment_doctor_id SET DEFAULT nextval('public.appointment_appointment_doctor_id_seq'::regclass);
 P   ALTER TABLE public.appointment ALTER COLUMN appointment_doctor_id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    61016     available_date available_date_id    DEFAULT     �   ALTER TABLE ONLY public.available_date ALTER COLUMN available_date_id SET DEFAULT nextval('public.available_date_available_date_id_seq'::regclass);
 O   ALTER TABLE public.available_date ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    221    223    223            �           2604    61017 '   available_date available_date_doctor_id    DEFAULT     �   ALTER TABLE ONLY public.available_date ALTER COLUMN available_date_doctor_id SET DEFAULT nextval('public.available_date_available_date_doctor_id_seq'::regclass);
 V   ALTER TABLE public.available_date ALTER COLUMN available_date_doctor_id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    61024    customers customer_id    DEFAULT     ~   ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);
 D   ALTER TABLE public.customers ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    224    225    225            �           2604    61033    doctors doctor_id    DEFAULT     v   ALTER TABLE ONLY public.doctors ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctors_doctor_id_seq'::regclass);
 @   ALTER TABLE public.doctors ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    227    226    227            �           2604    61043    vaccine vaccine_id    DEFAULT     x   ALTER TABLE ONLY public.vaccine ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccine_vaccine_id_seq'::regclass);
 A   ALTER TABLE public.vaccine ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    228    230    230            �           2604    61044    vaccine vaccine_animal_id    DEFAULT     �   ALTER TABLE ONLY public.vaccine ALTER COLUMN vaccine_animal_id SET DEFAULT nextval('public.vaccine_vaccine_animal_id_seq'::regclass);
 H   ALTER TABLE public.vaccine ALTER COLUMN vaccine_animal_id DROP DEFAULT;
       public          postgres    false    230    229    230            /          0    60991    animals 
   TABLE DATA           �   COPY public.animals (animal_id, animal_breed, animal_color, animal_birthday, animal_gender, animal_name, animal_species, animal_customer_id) FROM stdin;
    public          postgres    false    216   eY       3          0    61003    appointment 
   TABLE DATA           u   COPY public.appointment (appointment_id, appointment_date, appointment_animal_id, appointment_doctor_id) FROM stdin;
    public          postgres    false    220   UZ       6          0    61013    available_date 
   TABLE DATA           j   COPY public.available_date (available_date_id, available_date_date, available_date_doctor_id) FROM stdin;
    public          postgres    false    223   �Z       8          0    61021 	   customers 
   TABLE DATA              COPY public.customers (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    225   �Z       :          0    61030    doctors 
   TABLE DATA           q   COPY public.doctors (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    227   �\       =          0    61040    vaccine 
   TABLE DATA           �   COPY public.vaccine (vaccine_id, vaccine_code, vaccine_name, vaccine_protection_finish_date, vaccine_protection_start_date, vaccine_animal_id) FROM stdin;
    public          postgres    false    230   �]       O           0    0    animals_animal_customer_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.animals_animal_customer_id_seq', 1, false);
          public          postgres    false    215            P           0    0    animals_animal_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.animals_animal_id_seq', 6, true);
          public          postgres    false    214            Q           0    0 %   appointment_appointment_animal_id_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.appointment_appointment_animal_id_seq', 1, false);
          public          postgres    false    218            R           0    0 %   appointment_appointment_doctor_id_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.appointment_appointment_doctor_id_seq', 1, false);
          public          postgres    false    219            S           0    0    appointment_appointment_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.appointment_appointment_id_seq', 4, true);
          public          postgres    false    217            T           0    0 +   available_date_available_date_doctor_id_seq    SEQUENCE SET     Z   SELECT pg_catalog.setval('public.available_date_available_date_doctor_id_seq', 1, false);
          public          postgres    false    222            U           0    0 $   available_date_available_date_id_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.available_date_available_date_id_seq', 7, true);
          public          postgres    false    221            V           0    0    customers_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customers_customer_id_seq', 5, true);
          public          postgres    false    224            W           0    0    doctors_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctors_doctor_id_seq', 5, true);
          public          postgres    false    226            X           0    0    vaccine_vaccine_animal_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.vaccine_vaccine_animal_id_seq', 1, false);
          public          postgres    false    229            Y           0    0    vaccine_vaccine_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.vaccine_vaccine_id_seq', 5, true);
          public          postgres    false    228            �           2606    60999    animals animals_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    216            �           2606    61010    appointment appointment_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (appointment_id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    220            �           2606    61019 "   available_date available_date_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (available_date_id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    223            �           2606    61028    customers customers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    225            �           2606    61037    doctors doctors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (doctor_id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    227            �           2606    61048    vaccine vaccine_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (vaccine_id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    230            �           2606    61059 '   appointment fkac8nsrw2gj3esfv72x4ss26hu    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkac8nsrw2gj3esfv72x4ss26hu FOREIGN KEY (appointment_doctor_id) REFERENCES public.doctors(doctor_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkac8nsrw2gj3esfv72x4ss26hu;
       public          postgres    false    220    227    3223            �           2606    61054 '   appointment fkg5j1sntvkbnnikfyomfmn5g73    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkg5j1sntvkbnnikfyomfmn5g73 FOREIGN KEY (appointment_animal_id) REFERENCES public.animals(animal_id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkg5j1sntvkbnnikfyomfmn5g73;
       public          postgres    false    3215    220    216            �           2606    61049 #   animals fknjsvd8kplxqmf48ybxayrx6ru    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru FOREIGN KEY (animal_customer_id) REFERENCES public.customers(customer_id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru;
       public          postgres    false    216    3221    225            �           2606    61069 #   vaccine fkoqw78f6i79wcvc5oem3x68qft    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkoqw78f6i79wcvc5oem3x68qft FOREIGN KEY (vaccine_animal_id) REFERENCES public.animals(animal_id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fkoqw78f6i79wcvc5oem3x68qft;
       public          postgres    false    216    230    3215            �           2606    61064 *   available_date fkpaalwgfslp8gdi9ep8q8al16w    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkpaalwgfslp8gdi9ep8q8al16w FOREIGN KEY (available_date_doctor_id) REFERENCES public.doctors(doctor_id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fkpaalwgfslp8gdi9ep8q8al16w;
       public          postgres    false    223    227    3223            /   �   x�=��N�0�����l�@��Uˁ��A��e]/��Wk���	��|��@뱧D�f����RUR+�R�����8�3hQ��c��7J*-ͅ��&h<;0�6c>v�$�|'UO3�ڍ��h������� ��E��2R�_��[(��)�^%na�����l���l��%��H%4<�et�����W�̊�^I�����Ҍ�M������Z���U�      3   F   x�e��� ����4`t>�(���_D��{�&����/q���Veamy�@!?���eZ�6����� L��      6   B   x�=���0C�3�%88]���� ����0:s�e�Y�!��u�fxqbu�L���{��{I7�{ ���      8   |  x�]�An�0E��)� �!�dd�,;AQ�N��@6�L��DW$U�/k�E�W����_����j
�2|�r�k~D����q�r���x�.�7�(��2�]�M�ߵ8{��Ǝ∣!x�]LD
Z�G'�P����w&�Nv�Ps9~'�fh^����E*��g�Y'��=]��v�#��;���v���,˒\�r���ó��k~�=Ē�W���}!�v$f�8۞�Np�,`U�7b8����<�)�VE�ŵ��\�%�a���+;E�:G%�P�<+�kO4��Ec�%/v�?�!C���?��2QB�-|!�Oh��ƒyZ���]b��!u��ڈ�������a�s�������r�_P�8~�N)���$I~h���      :   a  x�m��N�0�g�)�Nd5���^���	�r�5�cWN�H^�7`�+Ѕ�[�{a��
���H����G���̸@3�P�U����SV�n/*���J�sW0�Iʱ����N0�V9�k
��� r�� 
<���8;��B���=�m�ԅ%Ӝ5FZ��i]�DI�L�����i�L�y��;X~2E$qa�{NHJo�\���9�%�۩�<�d(+�YA7��MQw��=�-f�mH��ӑ�Y�o+�լ�%nM�#�}�ѡ��w�9��6��lGQ����0���{mh�t|2)�l����ċ}��;�ȅ���&�j$C��6g%UMYi�O�˞C���7����8�8
�:��xj�      =   �   x�mο�0�����@M{Pt��@�\*�p�)��B0��t���i(�
'�_�ƶ-;;�� �R��ɞZ �Ai��i< ��曠�!��	�:vU�/��~��F�Tj��-c���<�V^�����>��o���<�|���?h��;������I�ĦMG     
package id.slava.nt.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}